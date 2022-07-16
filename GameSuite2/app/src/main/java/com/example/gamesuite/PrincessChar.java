package com.example.gamesuite;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

public class PrincessChar extends Character{
    boolean supercharged = false;
    int lives = 3;
    int points = 0;
    String direction = new String("");
    int downX, downY, upX, upY = 0;
    final int THRESHOLD = 100;
    boolean isDone = false;

    public void move() {
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
        downX = downY = upX = upY = 0;
        /*
        while (!isDone) {
            switch (direction) {
                case "RIGHT":
                    // continue moving right
                    xPos++;
                    Log.i("xPos", String.valueOf(xPos));
                    break;
                case "LEFT":
                    // continue moving left
                    xPos--;
                    break;
                case "DOWN":
                    // continue moving down
                    yPos++;
                    break;
                case "UP":
                    // continue moving up
                    yPos--;
                    break;
            }
        }
         */
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                Log.i("TAG", "touched down " + String.valueOf(downX) + " " + String.valueOf(downY));
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "moving:");
                break;
            case MotionEvent.ACTION_UP:
                upX = (int) event.getX();
                upY = (int) event.getY();
                Log.i("TAG", "touched up");
                move();
                break;
        }
        return true; // ???
    }

    @Override
    public void draw(Canvas c) {

    }
}
