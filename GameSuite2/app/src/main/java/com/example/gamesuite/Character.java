package com.example.gamesuite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Character {
    int size;
    static double momentumStrength = 0.85;
    int xPos;
    int yPos;
    int speed;
    int pictureID;
    Bitmap avatar;
    public abstract void move();
}
