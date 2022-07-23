package com.example.gamesuite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class chessActivity extends AppCompatActivity {
    public static Map<Pair<Integer, Integer>, chessPiece> boardPieces;
    public static int whiteColumn;
    public static int whiteRow;
    public static int blackColumn;
    public static int blackRow;
    public static boolean inCheck;
    public static boolean blackInCheck;
    public static boolean whiteInCheck;

    public static void resetPieces() {
        boardPieces = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            boardPieces.put(new Pair<>(i, 1), new pawn(i, 1,chessColor.WHITE));
        }
        for (int i = 0; i < 8; i++) {
            boardPieces.put(new Pair<>(i, 6), new pawn(i, 6,chessColor.BLACK));

        }
        for (int i = 0; i < 2; i++ ) {
            boardPieces.put(new Pair<>(i * 7, 0), new rook(i * 7, 0, chessColor.WHITE));
            boardPieces.put(new Pair<>(i * 7, 7), new rook(i * 7, 7, chessColor.BLACK));
            boardPieces.put(new Pair<>(1 + i * 5, 0), new knight(1 + i * 5, 0, chessColor.WHITE));
            boardPieces.put(new Pair<>(1 + i * 5, 7), new knight(1 + i * 5, 7, chessColor.BLACK));
            boardPieces.put(new Pair<>(2 + i * 3, 0), new bishop(2 + i * 3, 0, chessColor.WHITE));
            boardPieces.put(new Pair<>(2 + i * 3, 7), new bishop(2 + i * 3, 7, chessColor.BLACK));
        }
        boardPieces.put(new Pair<>(3, 0),new queen(3, 0, chessColor.WHITE));
        boardPieces.put(new Pair<>(3, 7),new queen(3, 7, chessColor.BLACK));
        boardPieces.put(new Pair<>(4, 0),new king(4, 0, chessColor.WHITE));
        boardPieces.put(new Pair<>(4, 7),new king(4, 7, chessColor.BLACK));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        resetPieces();
        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(chessActivity.this, MainActivity2.class);

            startActivity(intent);
        });
    }

    protected static void movePiece(int sColumn, int sRow, int fColumn, int fRow) {
        chessPiece piece = chessBoard.pieceAt(sColumn, sRow);
        if ( piece != null && fColumn >= 0 && fColumn <= 7 && fRow >= 0 && fRow <= 7) {piece.move(fColumn, fRow);
          piece.column = fColumn;
          piece.row = fRow;
          boardPieces.put(new Pair<>(sColumn, sRow), null);
          boardPieces.put(new Pair<>(fColumn, fRow), piece);
          Log.i("TAG", "moving piece: (" + fColumn + ", " + fRow + ")");
        }
    }
}