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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class chessBoard extends View {
    float startleft;
    float starttop;
    float squarelength = 130f;
    View view1;
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
        view1 = findViewById(R.id.chessboard);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int srow = (int) (startleft - event.getX()/squarelength);
        int scolumn = (int) (starttop - event.getY()/squarelength);

        int x = (int) event.getX();
        int y = (int) event.getY();
        int fcolumn = (int) ((event.getX() - startleft)/squarelength);
        int frow = (int) ((event.getY() - starttop)/squarelength);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scolumn = (int) ((event.getX() - startleft)/squarelength);
                srow = 7 - (int)((event.getY() - starttop)/squarelength);
                Log.i("TAG", "start: (" + srow + ", " + scolumn + ")");
                break;
            case MotionEvent.ACTION_MOVE:
                fcolumn = (int) ((event.getX() - startleft)/squarelength);
                frow = 7 - (int) ((event.getY() - starttop)/squarelength);
                Log.i("TAG", "moving: (" + frow + ", " + fcolumn + ")");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG", "touched up");
                break;
        }
        chessActivity.movePiece(scolumn, srow, frow, fcolumn);
        view1.invalidate();
        return true;
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
        for (chessPiece piece: chessActivity.pieces) {
            if (piece == null) {
                continue;
            } else {
                insertPiece(canvas, piece.column, piece.row, piece.id);
            }
        }
    }

    protected void insertPiece(Canvas canvas, int column, int row, int id) {
        Paint paint = new Paint();
        Bitmap piece = pieces.get(id);
        canvas.drawBitmap(piece, null, new RectF(startleft + column * squarelength, starttop + (7 - row) * squarelength, startleft + 130f + column * squarelength, starttop + 130f + (7 - row) * squarelength), paint);
    }

    protected static Integer pieceAt(int column, int row) {
        for (int i = 0; i < chessActivity.pieces.length; i++) {
            if (chessActivity.pieces[i] == null) {
                continue;
            }
            chessPiece piece = chessActivity.pieces[i];
            if (piece.column == column && piece.row == row) {
                return i;
            }
        }
        return null;
    }
}
