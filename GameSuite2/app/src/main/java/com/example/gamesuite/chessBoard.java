package com.example.gamesuite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Map;

public class chessBoard extends View {
    float startleft;
    float starttop;
    float squarelength = 130f;
    Map<Integer, Bitmap> pieces;
    public chessBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        pieces.put(R.drawable.blackbishop, BitmapFactory.decodeResource(getResources(), R.drawable.blackbishop));
        pieces.put(R.drawable.whitebishop, BitmapFactory.decodeResource(getResources(), R.drawable.whitebishop));
        pieces.put(R.drawable.blackknight, BitmapFactory.decodeResource(getResources(), R.drawable.blackknight));
        pieces.put(R.drawable.whitebishop, BitmapFactory.decodeResource(getResources(), R.drawable.whitebishop));

    }

    protected void drawBoard(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        startleft = getWidth()/2f - 130f *4;
        starttop = getHeight()/2f - 130f * 4;
        for (int column = 0; column < 8; column++ ) {
            for (int row = 0; row < 8; row++) {
                if ( row % 2 == 0) {
                    if (column % 2 == 1) {
                        paint.setColor(Color.LTGRAY);
                    } else {
                        paint.setColor(Color.DKGRAY);
                    }
                } else {
                    if (column % 2 == 0) {
                        paint.setColor(Color.LTGRAY);
                    } else {
                        paint.setColor(Color.DKGRAY);
                    }
                }
                canvas.drawRect(startleft + squarelength*column, starttop + squarelength * row, startleft + squarelength + squarelength * column, starttop + squarelength + squarelength * row, paint);
            }
        }
    }

    public void drawPiece(Canvas canvas, int column, int row, int pieceId) {
        startleft = getWidth()/2f - 130f *4;
        starttop = getHeight()/2f - 130f * 4;
        Bitmap piece = pieces.get(pieceId);
        canvas.drawBitmap(piece, null, RectF(startleft + (7 - column)*squarelength, ));
    }

//    private void drawBoard(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        for (int column = 0; column < 8; column++ ) {
//            for (int row = 0; row < 8; row++) {
//                if (column % 2 == 0 && row % 2 == 0) {
//                    paint.setColor(Color.DKGRAY);
//                } else if (column % 2 == 1 && row % 2 == 1) {
//                    paint.setColor(Color.DKGRAY);
//                } else {
//                    paint.setColor(Color.LTGRAY);
//                }
//                canvas.drawRect(100f + 130, 200f, 100f + squarelength + 130 * row, 200f + squarelength + 130 * row, paint);
//            }
//        }
//    }
}
