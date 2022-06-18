package com.example.gamesuite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ConstraintLayout layout2 = findViewById(R.id.layout);
        //goes to wordle screen...will replace button with ImageButton later
        ImageButton wordlePlay = (ImageButton) findViewById(R.id.wordleButton);
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
        //goes to chess screen
        ImageButton pcPlay = (ImageButton) findViewById(R.id.pcButton);
        pcPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, chessActivity.class);

            startActivity(intent);
        });
        //goes to cards screen
        ImageButton chessPlay = (ImageButton) findViewById(R.id.chessButton);
        chessPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, cardsActivity.class);

            startActivity(intent);
        });

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

        toggleButton1.setOnClickListener(v -> {
            if (toggleButton1.isChecked()) {
                layout2.setBackgroundResource(R.drawable.nighttwo);
                btn.setBackgroundColor(Color.parseColor("#A2AEFF"));
                chessPlay.setImageResource(R.drawable.nightchess);
                wordlePlay.setImageResource(R.drawable.nightwordle);
                pcPlay.setImageResource(R.drawable.nightpc);
            } else {
                layout2.setBackgroundResource(R.drawable.daytwo);
                btn.setBackgroundColor(Color.parseColor("#F6ED98"));
                chessPlay.setImageResource(R.drawable.daychess);
                wordlePlay.setImageResource(R.drawable.daywordle);
                pcPlay.setImageResource(R.drawable.daypc);
            }
        });

        Button about = findViewById(R.id.about);
        about.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, AboutActivity.class);
            startActivity(intent);
        });
    }

}
