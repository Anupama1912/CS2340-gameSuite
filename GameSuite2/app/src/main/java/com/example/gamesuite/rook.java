package com.example.gamesuite;

import android.util.Pair;

import java.util.HashMap;
import java.util.Set;

public class rook extends chessPiece{
    public rook(int column, int row, chessColor color) {
        super(column, row, color, chessRank.ROOK, (color == chessColor.BLACK)? R.drawable.blackrook: R.drawable.whiterook);
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
    boolean canCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row) {
        return false;
    }

}
