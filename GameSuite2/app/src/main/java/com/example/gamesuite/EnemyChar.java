package com.example.gamesuite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EnemyChar extends Character{
    int dirX = 1;
    int dirY = 0;
    int avatarNum;
    //public int prevAtCurr = 0;
    int x;
    int y;
    int times = 0;

    public EnemyChar(int x, int y, int a){
        this.speed = 4;
        this.pictureID = 0;
        this.x = x;
        this.y = y;
        this.size = 10;
        this.avatarNum = a;
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
    public void move() {
        times++;
        if(times >= 30){
            //System.out.println("Impossible to move");
            return;
        }
        if(!this.canMove()){
            //System.out.println("Can't move");
            randSwitchDir();
            move();
        } else {
            double randomizer = Math.random();
            if(randomizer <= momentumStrength){
                //System.out.println("Went forward");
                times = 0;
                //tileMap.currentmap[y][x] = prevAtCurr;
                x += dirX;
                y += dirY;
                if(tileMap.currentmap[y][x] == 4){
                    PrincessChar.lostLife();
                }
               /* int p = tileMap.currentmap[y][x];
                if(p == 4){
                    prevAtCurr = 5;
                } else if(p>=6 && p<=8) {
                    prevAtCurr = tileMap.currentmap[y][x];
                }
                tileMap.currentmap[y][x] = this.avatarNum;*/
            } else {
                //System.out.println("Still turned");
                randSwitchDir();
                this.move();
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
        //c.drawCircle(xPos, yPos, size/2, p);
    }

    public void randSwitchDir() {
        int newX;
        int newY;
        int randomizer = 1 + (int) (Math.random()*3);
        if(randomizer == 1) {
            newX = -dirX;
            newY = -dirY;
        }else if(randomizer == 2) {
            newX = -dirY;
            newY = dirX;
        } else {
            newX = dirY;
            newY = -dirX;
        }
        dirX = newX;
        dirY = newY;
    }

    public boolean canMove(){
        /*System.out.println(this.xPos);
        int i = c.tileAt(xPos ,
                yPos);
        */
        int newX = x + dirX;
        int newY = y + dirY;
        if(newX < 0 || newX > tileMap.currentmap[0].length) {
            return false;
        }
        if(newY < 0 || newY > tileMap.currentmap[0].length) {
            return false;
        }
        int i = tileMap.currentmap[newY][newX];
        return i != 1 && i != 2 && i != 6 && i != 7 && i != 8 ;
    }
}
