package com.example.gamesuite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

public class chessActivity extends AppCompatActivity {
    public static chessPiece[] pieces;

    public static void resetPieces() {
        pieces = new chessPiece[32];
        for (int i = 0; i < 8; i++) {
            pieces[i] = new pawn(i, 1,chessColor.WHITE);
        }
        for (int i = 8; i < 16; i++) {
            pieces[i] = new pawn(i - 8, 6,chessColor.BLACK);
        }
        int index = 16;
        for (int i = 0; i < 2; i++ ) {
            pieces[index++] = new rook(i * 7, 0, chessColor.WHITE);
            pieces[index++] = new rook(i * 7, 7, chessColor.BLACK);
            pieces[index++] = new knight(1 + i * 5, 0, chessColor.WHITE);
            pieces[index++] = new knight(1 + i * 5, 7, chessColor.BLACK);
            pieces[index++] = new bishop(2 + i * 3, 0, chessColor.WHITE);
            pieces[index++] = new bishop(2 + i * 3, 7, chessColor.BLACK);
        }
        pieces[index++] = new queen(3, 0, chessColor.WHITE);
        pieces[index++] = new queen(3, 7, chessColor.BLACK);
        pieces[index++] = new king(4, 0, chessColor.WHITE);
        pieces[index++] = new king(4, 7, chessColor.BLACK);

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
        Integer index = chessBoard.pieceAt(sColumn, sRow);
        if ( index != null) {
            pieces[index].column = fColumn;
            pieces[index].row = fRow;
            Log.i("TAG", "moving piece: (" + fColumn + ", " + fRow + ")");
        }
    }
}