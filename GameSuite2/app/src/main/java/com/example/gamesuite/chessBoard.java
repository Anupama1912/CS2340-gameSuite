package com.example.gamesuite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class chessBoard extends View {
    float startleft;
    float starttop;
    float squarelength = 130f;
    Map<Integer, Bitmap> pieces = new HashMap<>();
    public chessBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        startleft = getWidth()/2f - 130f *4;
        starttop = getHeight()/2f - 130f * 4;
        super.onDraw(canvas);
        drawBoard(canvas);
        pieces.put(R.drawable.blackbishop, BitmapFactory.decodeResource(getResources(), R.drawable.blackbishop));
        pieces.put(R.drawable.blackrook, BitmapFactory.decodeResource(getResources(), R.drawable.blackrook));
        pieces.put(R.drawable.blackknight, BitmapFactory.decodeResource(getResources(), R.drawable.blackknight));
        pieces.put(R.drawable.blackqueen, BitmapFactory.decodeResource(getResources(), R.drawable.blackqueen));
        pieces.put(R.drawable.blackking, BitmapFactory.decodeResource(getResources(), R.drawable.blackking));
        pieces.put(R.drawable.blackpawn, BitmapFactory.decodeResource(getResources(), R.drawable.blackpawn));
        pieces.put(R.drawable.whitebishop, BitmapFactory.decodeResource(getResources(), R.drawable.whitebishop));
        pieces.put(R.drawable.whiterook, BitmapFactory.decodeResource(getResources(), R.drawable.whiterook));
        pieces.put(R.drawable.whiteknight, BitmapFactory.decodeResource(getResources(), R.drawable.whiteknight));
        pieces.put(R.drawable.whitequeen, BitmapFactory.decodeResource(getResources(), R.drawable.whitequeen));
        pieces.put(R.drawable.whiteking, BitmapFactory.decodeResource(getResources(), R.drawable.whiteking));
        pieces.put(R.drawable.whitepawn, BitmapFactory.decodeResource(getResources(), R.drawable.whitepawn));
        initialLayout(canvas);
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
                        paint.setColor(Color.DKGRAY);
                    } else {
                        paint.setColor(Color.LTGRAY);
                    }
                } else {
                    if (column % 2 == 0) {
                        paint.setColor(Color.DKGRAY);
                    } else {
                        paint.setColor(Color.LTGRAY);
                    }
                }
                canvas.drawRect(startleft + squarelength*column, starttop + squarelength * row, startleft + squarelength + squarelength * column, starttop + squarelength + squarelength * row, paint);
            }
        }
    }

    protected void initialLayout(Canvas canvas) {
        for (int i = 0; i < 8; i++) {
            insertPiece(canvas, i,1, R.drawable.whitepawn);
            insertPiece(canvas, i, 6, R.drawable.blackpawn);
        }
        for (int i = 0; i < 2; i++) {
            insertPiece(canvas, 7 * i, 7, R.drawable.blackrook);
            insertPiece(canvas, 7 * i, 0, R.drawable.whiterook);
            insertPiece(canvas, 1 + 5 * i, 7, R.drawable.blackknight);
            insertPiece(canvas, 1 + 5 * i, 0, R.drawable.whiteknight);
            insertPiece(canvas, 2 + 3 * i, 7, R.drawable.blackbishop);
            insertPiece(canvas, 2 + 3 * i, 0, R.drawable.whitebishop);
        }
        insertPiece(canvas,3 , 7, R.drawable.blackqueen);
        insertPiece(canvas,3 , 0, R.drawable.whitequeen);
        insertPiece(canvas,4 , 7, R.drawable.blackking);
        insertPiece(canvas,4 , 0, R.drawable.whiteking);
    }

    protected void insertPiece(Canvas canvas, int column, int row, int id) {
        Paint paint = new Paint();
        Bitmap piece = pieces.get(id);
        canvas.drawBitmap(piece, null, new RectF(startleft + column * squarelength, starttop + (7 - row) * squarelength, startleft + 130f + column * squarelength, starttop + 130f + (7 - row) * squarelength), paint);
    }
}
