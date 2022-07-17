package com.example.gamesuite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class myCanvas extends View {
    Paint paint;
    Rect rect;
    public static Bitmap brick, pinkDot, tiles, specialDot, avatar, g1, g2, g3, lives;
    static float width;
    static float height;

    int size;
    static int vBox = tileMap.map.length, hBox = tileMap.map.length;
    EnemyChar[] enemies = new EnemyChar[3];
    public myCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        rect = new Rect();
        for(int i = 0; i < enemies.length; i++){
            enemies[i] = new EnemyChar(i*50 + 50, 40);
            System.out.println("Enemy " + i + " Pos: x = " + enemies[i].xPos + ", y = " + enemies[i].yPos);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.parseColor("#DAE2B6"));
        paint.setStrokeWidth(3);
        size = getWidth()/2 - 50;
        int top = getHeight()/2 - size;
        int left = getWidth()/2 - size;
        int right = getWidth()/2 + size;
        int bottom = getHeight()/2 + size;
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

        for(int i = 0; i < enemies.length; i++) {
            enemies[i].draw(canvas, paint);

        }
    }

    int tileAt(int x, int y) {
        int top = getHeight()/2 - size;
        int left = getWidth()/2 - size;
        int right = getWidth()/2 + size;
        int bottom = getHeight()/2 + size;
        int i = (int) ((x - left)/width);
        int j = (int) ((y - top)/height);
        return tileMap.map[i][j];
    }
}