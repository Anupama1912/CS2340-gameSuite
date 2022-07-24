package com.example.gamesuite;

import android.graphics.Canvas;
import android.util.Log;
import android.graphics.Bitmap;

import android.graphics.Paint;
import android.view.View;

public class PrincessChar extends Character {
    boolean supercharged = false;
    int points = 0;
    Bitmap avatar;
    String direction = "";
    final int THRESHOLD = 100;
    static boolean continueMove = true;
    static View view;

    public PrincessChar() {
        this.speed = 4;
    }

    public void move(int downX, int downY, int upX, int upY) {
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
    }

    public void moveTimer() {
        switch (direction) {
            case "RIGHT":
                // continue moving right
                if (princessRunActivity.currentMap.currentmap[yPos][xPos + 1] != 1 && princessRunActivity.currentMap.currentmap[yPos][xPos + 1] != 2) {
//                    if (lostLife(xPos + 1, yPos)) {
//                        break;
//                    }
                    ;
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 0;
                    xPos++;
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 4;
                } else {
                    continueMove = false;
                }
                Log.i("xPos", String.valueOf(xPos));
                //view.invalidate();
                break;
            case "LEFT":
                // continue moving left
                if (princessRunActivity.currentMap.currentmap[yPos][xPos - 1] != 1 && princessRunActivity.currentMap.currentmap[yPos][xPos - 1] != 2) {
//                    if (lostLife(xPos - 1, yPos)) {
//                        break;
//                    }
                    ;
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 0;
                    xPos--;
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 4;
                } else {
                    continueMove = false;
                }
                Log.i("xPos", String.valueOf(xPos));
                //view.invalidate();
                break;
            case "DOWN":
                // continue moving down
                if (princessRunActivity.currentMap.currentmap[yPos + 1][xPos] != 1 && princessRunActivity.currentMap.currentmap[yPos + 1][xPos] != 2) {
//                    if (lostLife(xPos, yPos + 1)) {
//                        Log.i("lostLife", String.valueOf("true"));
//                        break;
//                    }
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 0;
                    yPos++;
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 4;
                } else {
                    continueMove = false;
                }
                Log.i("yPos", String.valueOf(yPos));
                //view.invalidate();
                break;
            case "UP":
                // continue moving up
                if (princessRunActivity.currentMap.currentmap[yPos - 1][xPos] != 1 && princessRunActivity.currentMap.currentmap[yPos - 1][xPos] != 2) {
//                    if (lostLife(xPos, yPos - 1)) {
//                        break;
//                    }
                    ;
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 0;
                    yPos--;
                    princessRunActivity.currentMap.currentmap[yPos][xPos] = 4;
                } else {
                    continueMove = false;
                }
                Log.i("yPos", String.valueOf(yPos));
                //view.invalidate();
                break;
        }
    }

    @Override
    public void move() {
    }

    public static void lostLife() {
        boolean lost = false;
        /*if (princessRunActivity.currentMap.currentmap[yPos][xPos] == 6 || princessRunActivity.currentMap.currentmap[yPos][xPos] == 7 || princessRunActivity.currentMap.currentmap[yPos][xPos] == 8) {
            tileMap.setLivesCount(tileMap.livesCount - 1);
            lost = true;
        }*/
        princessRunActivity.currentMap.setLivesCount(princessRunActivity.currentMap.getLivesCount() - 1);
        if (princessRunActivity.currentMap.getLivesCount() == 0) {
            Log.i("life", "Game Over!");
            myCanvas.timer.cancel();
            //direction = "";
            //Log.i("dir", direction);

            //game over
        }
        Log.i("life", String.valueOf(princessRunActivity.currentMap.getLivesCount()));
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
