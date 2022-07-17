package com.example.gamesuite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EnemyChar extends Character{
    int dir = 90;

    public EnemyChar(int x, int y){
        this.speed = 4;
        this.pictureID = 0;
        this.xPos = x;
        this.yPos = y;
        this.size = 10;
    }

    //0, no movement, 1 E, 2 W, 3 N, 4 S
    /* pseudo-code
    array of 4 booleans:
    loop through and assign true or false
    variable for how many possibilities
    80% chance try going in current direction
        if can: move
        if cant: assign prob to values in array
        pick one and move, update current direction
    20% chance pick random of other 3
        if can: move, update current direction
        if can't divide possible movements among equal prob pick one move update current dir
     */

    /* ALt Idea:
    Direction indicated by 0, 90, 180, 270
    if can't move:
        direction = (direction + (1,2,3)*90) % 360
        this.move();
    else:
        randomizer
        75% chance: go forward;
        25% chance: switch direction, this.move
     */
    @Override
    public void move(myCanvas c) {
        if(!this.canMove(c)){
            randSwitchDir();
            move(c);
        } else {
            double randomizer = Math.random();
            if(randomizer <= 0.75){
                this.xPos += (int) Math.cos(Math.toRadians(dir)) * speed;
                this.yPos += (int) Math.sin(Math.toRadians(dir)) * speed;
            } else {
                randSwitchDir();
                this.move(c);
            }
        }

    }

    /*
    double randomizer = 0;
        boolean[] directions;
        randomizer = Math.random();
        if(randomizer <= 0.8){
            if(xDirection != 0){
                this.xPos = xDirection*speed;
            } else {
                this.yPos = yDirection*speed;
            }
        } else {
            randomizer = Math.random();
            if(randomizer <= 0.5){
                if(xDirection != 0){
                    this.xDirection = -xDirection;
                    this.xPos = xDirection*speed;
                } else {
                    this.yDirection = -yDirection;
                    this.yPos = yDirection*speed;
                }
            } else {
                if(xDirection != 0){
                    this.xDirection = 0;

                    this.xPos = xDirection*speed;
                } else {
                    this.yDirection = 0;

                    this.yPos = yDirection*speed;
                }
            }
        }
     */
    @Override
    public void draw(Canvas c, Paint p) {
        c.drawCircle(xPos, yPos, size/2, p);
    }

    public void randSwitchDir() {
        int randomizer = 1 + (int) (Math.random()*3);
        this.dir = (dir + (randomizer*90)) % 360;
    }

    public boolean canMove(myCanvas c){
        int i = c.tileAt(xPos + (int) Math.cos(Math.toRadians(dir)) * speed,
                yPos + (int) Math.sin(Math.toRadians(dir)) * speed);
        return i != 1 && i != 2;
    }
}
