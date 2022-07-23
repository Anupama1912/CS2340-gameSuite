package com.example.gamesuite;

import android.util.Pair;

import java.util.HashMap;
import java.util.Set;

public class knight extends chessPiece{

    public knight(int column, int row, chessColor color) {
        super(column, row, color, chessRank.KNIGHT, (color == chessColor.BLACK)? R.drawable.blackknight: R.drawable.whiteknight);
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
