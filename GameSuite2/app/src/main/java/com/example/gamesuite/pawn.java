package com.example.gamesuite;

import android.util.Pair;

import java.util.Set;

public class pawn extends chessPiece{
    public pawn(int column, int row, chessColor color) {
        super(column, row, color, chessRank.PAWN, (color == chessColor.BLACK)? R.drawable.blackpawn: R.drawable.whitepawn);
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
    boolean canCheck(int column, int row) {
        return false;
    }
}
