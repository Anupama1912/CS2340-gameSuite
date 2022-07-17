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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class myCanvas extends View {
    Paint paint;
    Rect rect;
    public static Bitmap brick, pinkDot, tiles, specialDot, avatar, g1, g2, g3;
    int size = 600;
    int top, left, right,bottom;
    int vBox = tileMap.map.length, hBox = tileMap.map.length;
    float width;
    float height;
    int downX, downY, upX, upY = 0;

    PrincessChar princess = new PrincessChar();

    public myCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.parseColor("#DAE2B6"));
        paint.setStrokeWidth(3);
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
        specialDot = BitmapFactory.decodeResource(getResources(), R.drawable.glassshoes);
        avatar = BitmapFactory.decodeResource(getResources(), R.drawable.cindy);
        princess.setAvatar(avatar);
        g1 = BitmapFactory.decodeResource(getResources(), R.drawable.cindyg1);
        g2 = BitmapFactory.decodeResource(getResources(), R.drawable.cingyg2);
        g3 = BitmapFactory.decodeResource(getResources(), R.drawable.cindyg3);
        for (int row = 0; row < tileMap.map.length; row++) {
            for (int column = 0; column < tileMap.map[row].length; column++) {
                if (tileMap.map[row][column] == 1) {
                    canvas.drawBitmap(brick, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.map[row][column] == 2) {
                    canvas.drawBitmap(tiles, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.map[ row][column] == 3) {
                    canvas.drawBitmap(specialDot, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.map[row][column] == 4) {
                    Log.i("Pos", String.valueOf(princess.getXPos()) + " " + String.valueOf(princess.getYPos()));
                    princess.setXPos(column);
                    princess.setYPos(row);
                    canvas.drawBitmap(princess.getAvatar(), null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
                if (tileMap.map[row][column] == 5) {
                    canvas.drawBitmap(pinkDot, null, new RectF(left + column * width, top + row * height, left + column * width + width - 10, top + row * height + height - 10),paint);
                }
                if (tileMap.map[row][column] == 6) {
                    canvas.drawBitmap(g1, null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
                if (tileMap.map[row][column] == 7) {
                    canvas.drawBitmap(g2, null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
                if (tileMap.map[row][column] == 8) {
                    canvas.drawBitmap(g3, null, new RectF(left + column * width, top + row * height - 15, left + column * width + width + 15, top + row * height + height),paint );
                }
            }
        }
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
                princess.move(downX, downY, upX, upY, width, height);
                invalidate();
                downX = downY = upX = upY = 0;
                /*
                new Timer().scheduleAtFixedRate(new TimerTask()
                {
                    @Override
                    public void run() {
                        princess.move(downX, downY, upX, upY, width, height);
                        invalidate();
                        downX = downY = upX = upY = 0;
                    }
                }, 0, 1000);
                 */
        }
        return true; // ???
    }
}