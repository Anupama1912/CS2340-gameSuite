package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

public class princessRunActivity extends AppCompatActivity {
    myCanvas canvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //tileMap map = new tileMap(tileSize);
        canvas = new myCanvas(this);
        canvas.setBackgroundColor(Color.parseColor("#3f3851"));
        setContentView(canvas);
    }
}




