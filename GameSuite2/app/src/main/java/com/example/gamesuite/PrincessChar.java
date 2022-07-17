package com.example.gamesuite;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.graphics.Bitmap;

import java.util.Timer;

public class PrincessChar extends Character{
    boolean supercharged = false;
    int lives = 3;
    int points = 0;
    Bitmap avatar;
    String direction = new String("");
    final int THRESHOLD = 100;
    boolean continueMove = true;
    Timer timer;

    public PrincessChar() {
        this.speed = 4;
    }

    public void move() {
        return;
    }
    public void move(int downX, int downY, int upX, int upY, float width, float height) {
        int diffX = upX - downX;
        int diffY = upY - downY;
        Log.i("DIFFX", String.valueOf(diffX));
        Log.i("DIFFY", String.valueOf(diffY));
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > THRESHOLD) {
                if (diffX > 0) {
                    direction = "RIGHT";
                } else {
                    direction = "LEFT";
                }
            }
        } else if (Math.abs(diffY) > THRESHOLD) {
            if (diffY > 0) {
                direction = "DOWN";
            } else {
                direction = "UP";
            }
        }
        Log.i("DIR", direction);
        Log.i("xPos", String.valueOf(xPos));
        Log.i("yPos", String.valueOf(yPos));
        //while (continueMove) {
            Log.i("if", "ahhh");
            switch (direction) {
                case "RIGHT":
                    // continue moving right
                    if (tileMap.map[yPos][xPos + 1] != 1 && tileMap.map[yPos][xPos + 1] != 2) {
                        lostLife(xPos + 1, yPos);
                        tileMap.map[yPos][xPos] = 0;
                        xPos++;
                        tileMap.map[yPos][xPos] = 4;
                    } else {
                        continueMove = false;
                    }
                    Log.i("xPos", String.valueOf(xPos));
                    break;
                case "LEFT":
                    // continue moving left
                    if (tileMap.map[yPos][xPos - 1] != 1 && tileMap.map[yPos][xPos - 1] != 2) {
                        lostLife(xPos - 1, yPos);
                        tileMap.map[yPos][xPos] = 0;
                        xPos--;
                        tileMap.map[yPos][xPos] = 4;
                    } else {
                        continueMove = false;
                    }
                    Log.i("xPos", String.valueOf(xPos));
                    break;
                case "DOWN":
                    // continue moving down
                    if (tileMap.map[yPos + 1][xPos] != 1 && tileMap.map[yPos + 1][xPos] != 2) {
                        lostLife(xPos, yPos + 1);
                        tileMap.map[yPos][xPos] = 0;
                        yPos++;
                        tileMap.map[yPos][xPos] = 4;
                    } else {
                        continueMove = false;
                    }
                    Log.i("yPos", String.valueOf(yPos));
                    break;
                case "UP":
                    // continue moving up
                    if (tileMap.map[yPos - 1][xPos] != 1 && tileMap.map[yPos - 1][xPos + 1] != 2) {
                        lostLife(xPos, yPos - 1);
                        tileMap.map[yPos][xPos] = 0;
                        yPos--;
                        tileMap.map[yPos][xPos] = 4;
                    } else {
                        continueMove = false;
                    }
                    Log.i("yPos", String.valueOf(yPos));
                    break;
            }
            Log.i("yPos", "end");
    }

    public void lostLife(int xPos, int yPos) {
        if (tileMap.map[yPos][xPos] == 6 || tileMap.map[yPos][xPos] == 7 || tileMap.map[yPos][xPos] == 8) {
            lives--;
        }
        if (lives == 0) {
            Log.i("life", "Game Over!");
            //game over
        }
        Log.i("life", String.valueOf(lives));
    }

    @Override
    public void draw(Canvas c) {

    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
