package com.example.gamesuite;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;


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
        Button about = findViewById(R.id.about);

        toggleButton1.setOnClickListener(v -> {
            if (toggleButton1.isChecked()) {
                layout2.setBackgroundResource(R.drawable.bnightplaceholder);
                btn.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                about.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                about.setTextColor(getResources().getColor(R.color.nButtonTxt));
            } else {
                layout2.setBackgroundResource(R.drawable.bdayplaceholder);
                btn.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                about.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                about.setTextColor(getResources().getColor(R.color.dButtonTxt));
            }
        });
        //goes to wordle screen...will replace button with ImageButton later
        ImageButton wordlePlay = (ImageButton) findViewById(R.id.wordleButton);
        wordlePlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, WordleActivity.class);

            startActivity(intent);
        });
        //goes to chess screen
        ImageButton chessPlay = (ImageButton) findViewById(R.id.chessButton);
        chessPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, chessActivity.class);

            startActivity(intent);
        });
        //goes to cards screen
        ImageButton cardsPlay = (ImageButton) findViewById(R.id.cardsButton);
        cardsPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, cardsActivity.class);

            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that About Button works!");
            showAbout();
        });
    }

    void showAbout() {
        Dialog aboutGame = new Dialog(MainActivity2.this);
        //Have already added custom title in layout. So disable the default title
        aboutGame.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Can cancel the dialog by clicking anywhere outside the dialog
        aboutGame.setCancelable(true);
        //Set layout of dialog
        aboutGame.setContentView(R.layout.about_game);

        ImageButton close = aboutGame.findViewById(R.id.close);
        close.setOnClickListener(v -> aboutGame.dismiss());

        aboutGame.show();
    }

}
