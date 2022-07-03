package com.example.gamesuite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class chessBoard extends View {
    float startleft;
    float starttop;
    float squarelength = 130f;
    public chessBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawBoard(canvas);
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
                canvas.drawRect(startleft + 130f*column, starttop + 130f * row, startleft + 130f + 130f * column, starttop + 130f + 130f * row, paint);
            }
        }
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
