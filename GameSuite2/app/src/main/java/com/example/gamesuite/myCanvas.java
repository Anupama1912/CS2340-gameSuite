package com.example.gamesuite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class myCanvas extends View {
    Paint paint;
    Rect rect;
    public static Bitmap brick, yellowDot, tiles;
    static float width;
    static float height;
    int size = 600;
    static int vBox = tileMap.map.length, hBox = tileMap.map.length;
    public myCanvas(Context context) {
        super(context);
        paint = new Paint();
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.parseColor("#dcc4bc"));
        paint.setStrokeWidth(3);
        int top = getHeight()/2 - size;
        int left = getWidth()/2 - size;
        int right = getWidth()/2 + size;
        int bottom = getHeight()/2 + size;
        canvas.drawRect(left,top, right, bottom, paint);
        width = (right - left)/vBox;
        height = (bottom - top)/hBox;
        brick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
        tiles = BitmapFactory.decodeResource(getResources(), R.drawable.tiles);
        yellowDot = BitmapFactory.decodeResource(getResources(), R.drawable.yellowcir);
        for (int row = 0; row < tileMap.map.length; row++) {
            for (int column = 0; column < tileMap.map[row].length; column++) {
                if (tileMap.map[row][column] == 1) {
                    canvas.drawBitmap(brick, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.map[row][column] == 2) {
                    canvas.drawBitmap(tiles, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
                if (tileMap.map[row][column] == 5) {
                    canvas.drawBitmap(yellowDot, null, new RectF(left + column * width, top + row * height, left + column * width + width, top + row * height + height),paint );
                }
            }
        }
    }
}