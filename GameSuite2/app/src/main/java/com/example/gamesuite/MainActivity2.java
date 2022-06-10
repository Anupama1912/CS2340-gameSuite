package com.example.gamesuite;

import static android.text.InputType.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
<<<<<<< HEAD
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
=======
import android.widget.Button;
>>>>>>> f7aa2e5255dbf4276260f417514fc7b372cdf30e
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FrameLayout layout2 = findViewById(R.id.layout);

        EditText editText = findViewById(R.id.editText);
        KeyboardClass keyboard = findViewById(R.id.keyboard);
        editText.setRawInputType(TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        ImageButton btn = findViewById(R.id.Back);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);

            startActivity(intent);
        });
        ToggleButton toggleButton1 = findViewById(R.id.toggleButton);

        toggleButton1.setOnClickListener(v -> {
            if (toggleButton1.isChecked()) {
                layout2.setBackgroundResource(R.drawable.bnightplaceholder);
            } else {
                layout2.setBackgroundResource(R.drawable.bdayplaceholder);
            }
        });
        //goes to wordle screen...will replace button with ImageButton later
        Button wordlePlay = (Button) findViewById(R.id.wordleButton);
        wordlePlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, WordleActivity.class);

            startActivity(intent);
        });
    }
}