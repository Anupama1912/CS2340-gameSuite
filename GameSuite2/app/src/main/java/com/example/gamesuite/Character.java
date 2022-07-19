package com.example.gamesuite;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Character {
    int size;
    static double momentumStrength = 0.85;
    static int xPos;
    static int yPos;
    int speed;
    int pictureID;
    public abstract void move();
    public abstract void draw(Canvas c, Paint p);
}
