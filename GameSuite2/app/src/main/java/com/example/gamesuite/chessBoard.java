package com.example.gamesuite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class chessBoard extends View {
    float startleft;
    float starttop;
    float squarelength = 130f;
    int scolumn;
    int srow;
    int row;
    int column;
    boolean showPieces = false;
    Pair<Integer, Integer> pair;
    View view1;
    Map<Integer, Bitmap> pieces = new HashMap<>();
    public chessBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        startleft = getWidth()/2f - 130f *4;
        starttop = getHeight()/2f - 130f * 4;
        super.onDraw(canvas);
        chessActivity.checkMoves();
        if (chessActivity.gameOver && chessActivity.inCheck) {
            MainActivity2.chessPlayed += 1;
            Toast toast = Toast.makeText(getContext(),"checkmate", Toast.LENGTH_SHORT);
            Log.i("gameover", "checkmate");
            paint.setColor(Color.WHITE);
            paint.setTextSize(70);
            canvas.drawText("checkmate", getWidth()/2 - 150, 1600, paint);
            toast.show();
        } else if (chessActivity.gameOver) {
            MainActivity2.chessPlayed += 1;
            Toast toast = Toast.makeText(getContext(),"stalemate", Toast.LENGTH_SHORT);
            toast.show();
            Log.i("gameover", "stalemate");
        }
        view1 = findViewById(R.id.chessboard);
//        chessActivity.checkMoves();
        Log.i("TAG", "checked all moves");
        if (showPieces) {
            drawPossMoves(canvas, column, row);
        } else {
            drawBoard(canvas);
        }
        showPieces = false;
        initialLayout(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int fcolumn = (int) ((event.getX() - startleft)/squarelength);
        int frow = 7 - (int) ((event.getY() - starttop)/squarelength);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "touched down");
                scolumn = (int) ((event.getX() - startleft) / squarelength);
                srow = 7 - (int) ((event.getY() - starttop) / squarelength);
                Log.i("TAG", "start: (" + srow + ", " + scolumn + ")");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "moving: (" + x + ", " + y + ")");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG", "touched up");
                Log.i("TAG", "starti: (" + srow + ", " + scolumn + ")");
                Log.i("TAG", "movingi: (" + frow + ", " + fcolumn + ")");
                Log.i("TAG", "touched up");
                fcolumn = (int) ((event.getX() - startleft) / squarelength);
                frow = 7 - (int) ((event.getY() - starttop) / squarelength);
                if (scolumn >= 0 && scolumn <= 7 && srow >= 0 && srow <= 7) {
                    if (scolumn != fcolumn || srow != frow) {
                        chessActivity.movePiece(scolumn, srow, fcolumn, frow);
                        view1.invalidate();
                    } else {
                        chessPiece piece = pieceAt(scolumn, srow);
                        if (piece != null && piece.color == chessActivity.player ) {
                            Log.i("checkpos", "piece clicked at:" + piece.column + "," + piece.row);
                            showPieces = true;
                            row = srow;
                            column = scolumn;
                            pair = new Pair<>(srow, scolumn);
                            view1.invalidate();
                        }
                    }
                }
                break;
        }

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
                        paint.setColor(Color.parseColor("#4d4c7d"));
                    } else {
                        paint.setColor(Color.parseColor("#FFFFFF"));
                    }
                } else {
                    if (column % 2 == 0) {
                        paint.setColor(Color.parseColor("#4d4c7d"));
                    } else {
                        paint.setColor(Color.parseColor("#FFFFFF"));
                    }
                }
                canvas.drawRect(startleft + squarelength*column, starttop + squarelength * row, startleft + squarelength + squarelength * column, starttop + squarelength + squarelength * row, paint);
            }
        }
    }

    protected void drawPossMoves(Canvas canvas, Integer scolumn, Integer srow) {
        Set<Pair<Integer, Integer>> moves = pieceAt(scolumn, srow).legalMoves;
        Paint paint = new Paint();
        startleft = getWidth()/2f - 130f *4;
        starttop = getHeight()/2f - 130f * 4;
        for (int column = 0; column < 8; column++ ) {
            for (int row = 0; row < 8; row++) {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
//                paint.clearShadowLayer();
                if (moves.contains(new Pair<>(column, 7 - row))) {
//                    paint.setShadowLayer(20, 0, 0, Color.parseColor("#BB7D7D"));
                    paint.setColor(Color.parseColor("#F7BECC"));
                } else {
                    if (row % 2 == 0) {
                        if (column % 2 == 1) {
                            paint.setColor(Color.parseColor("#4d4c7d"));
                        } else {
                            paint.setColor(Color.parseColor("#FFFFFF"));
                        }
                    } else {
                        if (column % 2 == 0) {
                            paint.setColor(Color.parseColor("#4d4c7d"));
                        } else {
                            paint.setColor(Color.parseColor("#FFFFFF"));
                        }
                    }
                }
                canvas.drawRect(startleft + squarelength*column, starttop + squarelength * row, startleft + squarelength + squarelength * column, starttop + squarelength + squarelength * row, paint);
            }
        }
    }

    protected void initialLayout(Canvas canvas) {
        for (chessPiece piece: chessActivity.boardPieces.values()) {
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

    protected static chessPiece pieceAt(int column, int row) {
        return chessActivity.boardPieces.get(new Pair<>(column, row));
    }
}
