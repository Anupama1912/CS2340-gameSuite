package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

public class princessRunActivity extends AppCompatActivity {
//    myCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //tileMap map = new tileMap(tileSize);
//        canvas = new myCanvas(this);
//        canvas.setBackgroundColor(Color.parseColor("#3f3851"));
        setContentView(R.layout.activity_princessrun);

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




