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
        for (int i = 0; i < 7; i++) {
            pieces[i] = new chessPiece(i, 1,chessColor.WHITE, chessRank.PAWN );
        }
        for (int i = 7; i < 14; i++) {
            pieces[i] = new chessPiece(i, 6,chessColor.BLACK, chessRank.PAWN );
        }
        int index = 14;
        for (int i = 0; i < 2; i++ ) {
            pieces[index++] = new chessPiece(i * 7, 0, chessColor.WHITE, chessRank.ROOK);
            pieces[index++] = new chessPiece(i * 7, 7, chessColor.BLACK, chessRank.ROOK);
            pieces[index++] = new chessPiece(1 + i * 5, 0, chessColor.WHITE, chessRank.KNIGHT);
            pieces[index++] = new chessPiece(1 + i * 5, 7, chessColor.BLACK, chessRank.KNIGHT);
            pieces[index++] = new chessPiece(2 + i * 5, 0, chessColor.WHITE, chessRank.BISHOP);
            pieces[index++] = new chessPiece(2 + i * 5, 7, chessColor.BLACK, chessRank.BISHOP);
        }
        pieces[index++] = new chessPiece(3, 0, chessColor.WHITE, chessRank.QUEEN);
        pieces[index++] = new chessPiece(3, 7, chessColor.BLACK, chessRank.QUEEN);
        pieces[index++] = new chessPiece(4, 0, chessColor.WHITE, chessRank.KING);
        pieces[index++] = new chessPiece(4, 7, chessColor.BLACK, chessRank.KING);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);

        ImageButton btn = findViewById(R.id.BackGame);
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(chessActivity.this, MainActivity2.class);

            startActivity(intent);
        });
    }
}