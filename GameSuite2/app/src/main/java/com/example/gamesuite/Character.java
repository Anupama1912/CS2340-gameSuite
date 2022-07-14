package com.example.gamesuite;

import android.graphics.Canvas;

public abstract class Character {
    int size;
    int xPos;
    int yPos;
    int speed;
    int pictureID;
    public abstract void move();
    public abstract void draw(Canvas c);
}
