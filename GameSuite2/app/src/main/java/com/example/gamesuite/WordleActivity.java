package com.example.gamesuite;

import static android.text.InputType.TYPE_CLASS_TEXT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class WordleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordle);
        FrameLayout layout2 = findViewById(R.id.layout);

        EditText editText = findViewById(R.id.editText);
        KeyboardClass keyboard = findViewById(R.id.keyboard);
        editText.setRawInputType(TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(WordleActivity.this, MainActivity2.class);

            startActivity(intent);
        });

    }
}