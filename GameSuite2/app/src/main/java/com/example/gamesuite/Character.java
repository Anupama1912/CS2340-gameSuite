package com.example.gamesuite;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Character {
    int size;
    static int xPos;
    static int yPos;
    int speed;
    int pictureID;
    public abstract void move(myCanvas c);
    public abstract void draw(Canvas c, Paint p);
}
