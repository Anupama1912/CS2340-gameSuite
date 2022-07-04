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
            pieces[i] = new chessPiece(i, 1,chessColor.WHITE, chessRank.PAWN, R.drawable.whitepawn );
        }
        for (int i = 8; i < 16; i++) {
            pieces[i] = new chessPiece(i - 8, 6,chessColor.BLACK, chessRank.PAWN, R.drawable.blackpawn);
        }
        int index = 16;
        for (int i = 0; i < 2; i++ ) {
            pieces[index++] = new chessPiece(i * 7, 0, chessColor.WHITE, chessRank.ROOK, R.drawable.whiterook);
            pieces[index++] = new chessPiece(i * 7, 7, chessColor.BLACK, chessRank.ROOK, R.drawable.blackrook);
            pieces[index++] = new chessPiece(1 + i * 5, 0, chessColor.WHITE, chessRank.KNIGHT, R.drawable.whiteknight);
            pieces[index++] = new chessPiece(1 + i * 5, 7, chessColor.BLACK, chessRank.KNIGHT, R.drawable.blackknight);
            pieces[index++] = new chessPiece(2 + i * 3, 0, chessColor.WHITE, chessRank.BISHOP, R.drawable.whitebishop);
            pieces[index++] = new chessPiece(2 + i * 3, 7, chessColor.BLACK, chessRank.BISHOP, R.drawable.blackbishop);
        }
        pieces[index++] = new chessPiece(3, 0, chessColor.WHITE, chessRank.QUEEN, R.drawable.whitequeen);
        pieces[index++] = new chessPiece(3, 7, chessColor.BLACK, chessRank.QUEEN, R.drawable.blackqueen);
        pieces[index++] = new chessPiece(4, 0, chessColor.WHITE, chessRank.KING, R.drawable.whiteking);
        pieces[index++] = new chessPiece(4, 7, chessColor.BLACK, chessRank.KING, R.drawable.blackking);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);

        ImageButton btn = findViewById(R.id.BackGame);
        resetPieces();
        btn.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Back Button Works!");
            Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT)
                    .show();
            Intent intent = new Intent(chessActivity.this, MainActivity2.class);

            startActivity(intent);
        });
    }
}