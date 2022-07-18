package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class princessRunActivity extends AppCompatActivity {
    myCanvas canvas;
    static tileMap currentMap = new tileMap(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //tileMap map = new tileMap(tileSize);
//        canvas = new myCanvas(this);
//        canvas.setBackgroundColor(Color.parseColor("#3f3851"));
        setContentView(R.layout.activity_princessrun);
        ImageButton easyMode = findViewById(R.id.easyMode);
        easyMode.setOnClickListener(v -> {
            currentMap.setTileNum(1);
            View curr = findViewById(R.id.maze);
            curr.invalidate();
        });

        ImageButton hardMode = findViewById(R.id.hardMode);
        hardMode.setOnClickListener(v -> {
            currentMap.setTileNum(2);
            View curr = findViewById(R.id.maze);
            curr.invalidate();
        });

        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(princessRunActivity.this, MainActivity2.class);

            startActivity(intent);
        });
    }
}




