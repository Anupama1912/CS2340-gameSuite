package com.example.gamesuite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class myCanvas extends View {
    Paint paint;
    Rect rect;
    public static Bitmap brick, pinkDot, tiles, specialDot, avatar, g1, g2, g3, lives;
    static float width;
    static float height;
    int top, left, right,bottom;
    int downX, downY, upX, upY = 0;
    static Timer timer = new Timer();
    static String direction = "";

    int size;
    static int vBox = tileMap.map.length, hBox = tileMap.map.length;
    PrincessChar princess = new PrincessChar();
    EnemyChar[] enemies = new EnemyChar[3];
    //EnemyChar enemy = new EnemyChar(16, 16, 8);

    public myCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        rect = new Rect();
        for(int i = 0; i < enemies.length; i++){
            enemies[i] = new EnemyChar(14+i, 16, 6+i);
            enemies[i].prevAtCurr = tileMap.currentmap[enemies[i].y][enemies[i].x];
            //System.out.println("Enemy " + i + " Pos: x = " + enemies[i].xPos + ", y = " + enemies[i].yPos);
        }
        myCanvas temp = this;

        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run() {
                if (direction != "") {
                    princess.moveTimer(direction);
                    for (int i = 0; i < enemies.length; i++) {
                        EnemyChar enemy = enemies[i];
                        enemy.move();
                        //System.out.println("Enemy#" + (i + 1) + " X: " + enemy.x + ", Y: " + enemy.y);
                    }
                    invalidate();
                } else {

                }
            }
        }, 0, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.parseColor("#DAE2B6"));
        paint.setStrokeWidth(3);
        size = getWidth()/2 - 50;
        top = getHeight()/2 - size;
        left = getWidth()/2 - size;
        right = getWidth()/2 + size;
        bottom = getHeight()/2 + size;
        canvas.drawRect(left,top, right, bottom, paint);
        width = (right - left)/vBox;
        height = (bottom - top)/hBox;
        brick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
        tiles = BitmapFactory.decodeResource(getResources(), R.drawable.tiles);
        pinkDot = BitmapFactory.decodeResource(getResources(), R.drawable.pinkcir);
        lives = BitmapFactory.decodeResource(getResources(),R.drawable.lives);

        if (princessRunActivity.currentMap.getTileNum() == 1){
            specialDot = BitmapFactory.decodeResource(getResources(), R.drawable.glassshoes);
            avatar = BitmapFactory.decodeResource(getResources(), R.drawable.cindy);
            g1 = BitmapFactory.decodeResource(getResources(), R.drawable.cindyg1);
            g2 = BitmapFactory.decodeResource(getResources(), R.drawable.cingyg2);
            g3 = BitmapFactory.decodeResource(getResources(), R.drawable.cindyg3);

        }
        if (princessRunActivity.currentMap.getTileNum() == 2){
            specialDot = BitmapFactory.decodeResource(getResources(), R.drawable.lamp);
            avatar = BitmapFactory.decodeResource(getResources(), R.drawable.jas);
            g1 = BitmapFactory.decodeResource(getResources(), R.drawable.jasg1);
            g2 = BitmapFactory.decodeResource(getResources(), R.drawable.jasg2);
            g3 = BitmapFactory.decodeResource(getResources(), R.drawable.jasg3);
        }
        princess.setAvatar(avatar);
        for (int row = 0; row < tileMap.currentmap.length; row++) {
            for (int column = 0; column < tileMap.map[row].length; column++) {
                if (tileMap.currentmap[row][column] == 1) {
                    canvas.drawBitmap(brick, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.currentmap[row][column] == 2) {
                    canvas.drawBitmap(tiles, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.currentmap[row][column] == 3) {
                    canvas.drawBitmap(specialDot, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.currentmap[row][column] == 4) {
                    Log.i("Pos", String.valueOf(princess.getXPos()) + " " + String.valueOf(princess.getYPos()));
                    princess.setXPos(column);
                    princess.setYPos(row);
                    canvas.drawBitmap(avatar, null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
                if (tileMap.currentmap[row][column] == 5) {
                    canvas.drawBitmap(pinkDot, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint);
                }
                if (tileMap.currentmap[row][column] == 6) {
                    canvas.drawBitmap(g1, null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
                if (tileMap.currentmap[row][column] == 7) {
                    canvas.drawBitmap(g2, null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
                if (tileMap.currentmap[row][column] == 8) {
                    canvas.drawBitmap(g3, null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
            }
        }
        for (int live = 0; live < tileMap.livesCount; live++) {
            canvas.drawBitmap(lives, null, new RectF(left + width * live, bottom + 10,left + width + width * live, bottom + height),paint);
        }

        /*for(int i = 0; i < enemies.length; i++) {
            canvas.drawBitmap(g1, null,
                    new RectF(enemies[i].xPos - enemies[i].size,
                            enemies[i].yPos-enemies[i].size,
                            enemies[i].xPos + enemies[i].size,
                            enemies[i].yPos-enemies[i].size),paint);
        }*/
    }

    int tileAt(int x, int y) {
        int top = getHeight()/2 - size;
        int left = getWidth()/2 - size;
        int right = getWidth()/2 + size;
        int bottom = getHeight()/2 + size;
        int r = x - left;
        float w = width;
        //System.out.println("x - left = " + r);
        //System.out.println("w = ", + w);
        int i = (int) ((x - left)/width);
        int j = (int) ((y - top)/height);
        return tileMap.map[j][i];
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        //timer.cancel();
        PrincessChar.view = findViewById(R.id.maze);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                Log.i("TAG", "touched down " + String.valueOf(downX) + " " + String.valueOf(downY));
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.i("TAG", "moving:");
                break;
            case MotionEvent.ACTION_UP:
                upX = (int) event.getX();
                upY = (int) event.getY();
                Log.i("TAG", "touched up");
                //princess.move(downX, downY, upX, upY, width, height);
                //invalidate();
                //downX = downY = upX = upY = 0;
                direction = princess.move(downX, downY, upX, upY);
                downX = downY = upX = upY = 0;
        }
        return true; // ???
    }
}