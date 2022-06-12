package com.example.gamesuite;

import static android.text.InputType.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Button;
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

        //Back button
        ImageButton btn = findViewById(R.id.Back);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);

            startActivity(intent);
        });
        ToggleButton toggleButton1 = findViewById(R.id.toggleButton);
        Button wordlePlay = (Button) findViewById(R.id.wordleButton);
        Button chessPlay = (Button) findViewById(R.id.chessButton);
        Button cardsPlay = (Button) findViewById(R.id.cardsButton);

        toggleButton1.setOnClickListener(v -> {
            if (toggleButton1.isChecked()) {
                layout2.setBackgroundResource(R.drawable.bnightplaceholder);
                wordlePlay.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                wordlePlay.setTextColor(getResources().getColor(R.color.nButtonTxt));
                chessPlay.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                chessPlay.setTextColor(getResources().getColor(R.color.nButtonTxt));
                cardsPlay.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                cardsPlay.setTextColor(getResources().getColor(R.color.nButtonTxt));
                btn.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
            } else {
                layout2.setBackgroundResource(R.drawable.bdayplaceholder);
                wordlePlay.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                wordlePlay.setTextColor(getResources().getColor(R.color.dButtonTxt));
                chessPlay.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                chessPlay.setTextColor(getResources().getColor(R.color.dButtonTxt));
                cardsPlay.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                cardsPlay.setTextColor(getResources().getColor(R.color.dButtonTxt));
                btn.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
            }
        });
        //goes to wordle screen...will replace button with ImageButton later
        wordlePlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, WordleActivity.class);

            startActivity(intent);
        });
        //goes to chess screen
        chessPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, chessActivity.class);

            startActivity(intent);
        });
        //goes to cards screen
        cardsPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, cardsActivity.class);

            startActivity(intent);
        });
    }
}