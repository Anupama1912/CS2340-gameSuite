package com.example.gamesuite;

import android.util.Pair;

import java.util.HashMap;
import java.util.Set;

public class bishop extends chessPiece{

    public bishop(int column, int row, chessColor color) {
        super(column, row, color, chessRank.BISHOP, (color == chessColor.BLACK)? R.drawable.blackbishop: R.drawable.whitebishop);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        return null;
    }

    @Override
    boolean validateMove(int column, int row) {
        return false;
    }

    @Override
    boolean move(int column, int row) {
        return false;
    }

    @Override
    boolean canCheck(HashMap<Pair<Integer, Integer>,chessPiece> chessPieces) {
        return false;
    }
}
