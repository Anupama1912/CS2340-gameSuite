package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        FrameLayout layout2 = (FrameLayout) findViewById(R.id.layout);

        ImageButton btn = (ImageButton) findViewById(R.id.Back);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);

            startActivity(intent);
        });
        ToggleButton toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton);

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
        //goes to chess screen
        Button chessPlay = (Button) findViewById(R.id.chessButton);
        chessPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, chessActivity.class);

            startActivity(intent);
        });
        //goes to cards screen
        Button cardsplay = (Button) findViewById(R.id.cardsButton);
        cardsplay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, cardsActivity.class);

            startActivity(intent);
        });
    }
}