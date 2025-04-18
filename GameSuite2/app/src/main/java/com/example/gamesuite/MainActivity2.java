package com.example.gamesuite;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;


public class MainActivity2 extends AppCompatActivity {
    static String user;
    static int prBestScore = 0, wordleBestScore = 0, chessPlayed = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ConstraintLayout layout2 = findViewById(R.id.layout);
        Log.i("name", user);
        //goes to wordle screen...will replace button with ImageButton later
        ImageButton wordlePlay = findViewById(R.id.wordleButton);
        wordlePlay.setOnClickListener(v -> {

            RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
            String url = "https://random-word-api.herokuapp.com/word?length=5";

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("word", response.toString());
                    WordleActivity.word = response.toString().substring(2,7);
                    Log.d("word1", WordleActivity.word);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("apierror", "something wrong");
                }
            });
            queue.add(request);
            Intent intent = new Intent(MainActivity2.this, WordleActivity.class);
            startActivity(intent);
        });
        //goes to Princess Run Screen
        ImageButton pcPlay = findViewById(R.id.pcButton);
        pcPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, princessRunWelcome.class);
            startActivity(intent);
        });
        //goes to Chess screen
        ImageButton chessPlay = findViewById(R.id.chessButton);
        chessPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, chessIntro.class);
            startActivity(intent);
        });

        //Back button
        Button btn = findViewById(R.id.exitGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Exit Button Works!");
            Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT)
                    .show();
            finishAffinity();
            System.exit(0);
        });

        ToggleButton toggleButton1 = findViewById(R.id.toggleButton);
        Button about = findViewById(R.id.about);
        Button stats = findViewById(R.id.stats);

        toggleButton1.setOnClickListener(v -> {
            if (toggleButton1.isChecked()) {
                layout2.setBackgroundResource(R.drawable.nighttwo);
                btn.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                btn.setTextColor(getResources().getColor(R.color.nButtonTxt));
                chessPlay.setImageResource(R.drawable.nightchess);
                wordlePlay.setImageResource(R.drawable.nightwordle);
                pcPlay.setImageResource(R.drawable.nightpc);
                about.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                about.setTextColor(getResources().getColor(R.color.nButtonTxt));
                stats.setBackgroundColor(getResources().getColor(R.color.nButtonBg));
                stats.setTextColor(getResources().getColor(R.color.nButtonTxt));
            } else {
                layout2.setBackgroundResource(R.drawable.daytwo);
                btn.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                btn.setTextColor(getResources().getColor(R.color.dButtonTxt));
                chessPlay.setImageResource(R.drawable.daychess);
                wordlePlay.setImageResource(R.drawable.daywordle);
                pcPlay.setImageResource(R.drawable.daypc);
                about.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                about.setTextColor(getResources().getColor(R.color.dButtonTxt));
                stats.setBackgroundColor(getResources().getColor(R.color.dButtonBg));
                stats.setTextColor(getResources().getColor(R.color.dButtonTxt));
            }
        });

        about.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that About Button works!");
            showAbout();
            //Intent intent = new Intent(MainActivity2.this, AboutActivity.class);
            //startActivity(intent);
        });



        stats.setOnClickListener(v -> {
            showStat();
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
        close.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT)
                    .show();
            aboutGame.dismiss();

        });
        aboutGame.show();
    }

    void showStat() {
        Dialog stats = new Dialog(MainActivity2.this);
        stats.requestWindowFeature(Window.FEATURE_NO_TITLE);
        stats.setCancelable(true);
        stats.setContentView(R.layout.scoreboard);
        TextView name = stats.findViewById(R.id.name);
        name.setText(user + "'s  Stats:");
        TextView wordle = stats.findViewById(R.id.wscore);
        TextView princess = stats.findViewById(R.id.pscore);
        TextView chess = stats.findViewById(R.id.cscore);
        wordle.setText(wordleBestScore + "");
        princess.setText(prBestScore + "");
        chess.setText(chessPlayed + "");
        stats.show();
    }

}
