package com.example.gamesuite;

import androidx.annotation.ContentView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class chessActivity extends AppCompatActivity {
    public static Map<Pair<Integer, Integer>, chessPiece> boardPieces;
    public static int moves;
    public static boolean inCheck;
    public static boolean gameOver;
    public static chessColor player;
    public static int wKingCol;
    public static int wKingRow;
    public static int bKingCol;
    public static int bKingRow;
    public static boolean blackInCheck;
    public static boolean whiteInCheck;


    public static void resetPieces() {
        player = chessColor.WHITE;
        inCheck = false;
        blackInCheck = false;
        whiteInCheck = false;
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
        wKingRow = 0;
        bKingRow = 7;
        wKingCol = 4;
        bKingCol = 4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inCheck = false;
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

        ImageButton restart = findViewById(R.id.restartBtn);
        restart.setOnClickListener(v -> {
            resetPieces();
            recreate();
        });

        if(gameOver) {
            Toast toast = Toast.makeText(getApplicationContext(),"checkmate", Toast.LENGTH_SHORT);
            toast.show();
        }

        ImageButton info = findViewById(R.id.infoChess);
        info.setOnClickListener(v -> {
            Log.i("My app", "This is for testing purposes that Info Button works!");
            showInstructions();
        });

        ImageButton stats = findViewById(R.id.stats);
        stats.setOnClickListener(v -> {
            showStat();
        });
    }

    void showInstructions() {
        Dialog instrChess = new Dialog(chessActivity.this);
        //Have already added custom title in layout. So disable the default title
        instrChess.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Can cancel the dialog by clicking anywhere outside the dialog
        instrChess.setCancelable(true);
        //Set layout of dialog
        instrChess.setContentView(R.layout.instr_chess);

        ImageButton close = instrChess.findViewById(R.id.close);
        close.setOnClickListener(v -> instrChess.dismiss());

        instrChess.show();
    }

    protected static void movePiece(int sColumn, int sRow, int fColumn, int fRow) {
        chessPiece piece = chessBoard.pieceAt(sColumn, sRow);
        if ( piece != null && piece.color == player && fColumn >= 0 && fColumn <= 7 && fRow >= 0 && fRow <= 7) {
//            piece.column = fColumn;
//            piece.row = fRow;
//            boardPieces.put(new Pair<>(sColumn, sRow), null);
//            boardPieces.put(new Pair<>(fColumn, fRow), piece);
            boolean move = piece.move(fColumn, fRow);
            if (move) {
                if (player == chessColor.WHITE) {
                    player = chessColor.BLACK;
                } else {
                    player = chessColor.WHITE;
                }
            }
            Log.i("TAG", "moving piece: (" + fColumn + ", " + fRow + ")");
        }
    }

    public static void checkMoves() {
        inCheck = blackInCheck || whiteInCheck;
        moves = 0;
        for (chessPiece piece : boardPieces.values()) {
            if (piece != null && piece.color == player) {
                piece.legalMoves = piece.getLegalMovements();
                moves += piece.legalMoves.size();
            }
        }
        if (moves == 0) {
            gameOver = true;
        }
        if (gameOver && inCheck) {
            Log.i("game ended", "checkmate");
        } else if (gameOver) {
            Log.i("gameover", "stalemate");
        }
    }

    public static boolean inTeamCheck(chessPiece piece) {
        if (piece.color == chessColor.BLACK) {
            return blackInCheck;
        } else {
            return whiteInCheck;
        }
    }

    public static boolean inoppCheck(chessPiece piece) {
        if (piece.color == chessColor.BLACK) {
            return whiteInCheck;
        } else {
            return blackInCheck;
        }
    }

    public static Pair<Integer, Integer> oppPostion(chessPiece piece) {
        if (piece.color == chessColor.BLACK) {
            return new Pair<>(wKingCol, wKingRow);
        } else {
            return new Pair<>(bKingCol, bKingRow);
        }
    }

    public static Pair<Integer, Integer> teamPostion(chessPiece piece) {
        if (piece.color == chessColor.BLACK) {
            return new Pair<>(bKingCol, bKingRow);
        } else {
            return new Pair<>(wKingCol, wKingRow);
        }
    }

    public static void setOppCheck(chessPiece piece, boolean check) {
        if (piece.color == chessColor.BLACK) {
            whiteInCheck = check;
        } else {
            blackInCheck = check;
        }
    }

    public static void setteamCheck(chessPiece piece, boolean check) {
        if (piece.color == chessColor.BLACK) {
            blackInCheck = check;
        } else {
            whiteInCheck = check;
        }
    }

    void showStat() {
        Dialog stats = new Dialog(chessActivity.this);
        stats.requestWindowFeature(Window.FEATURE_NO_TITLE);
        stats.setCancelable(true);
        stats.setContentView(R.layout.scoreboard);
        TextView name = stats.findViewById(R.id.name);
        name.setText(MainActivity2.user + " Stats");
        TextView wordle = stats.findViewById(R.id.wscore);
        TextView princess = stats.findViewById(R.id.pscore);
        TextView chess = stats.findViewById(R.id.cscore);
        wordle.setText(MainActivity2.wordleBestScore + "");
        princess.setText(MainActivity2.prBestScore + "");
        chess.setText(MainActivity2.chessPlayed + "");
        stats.show();
    }
}

