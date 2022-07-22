package com.example.gamesuite;

import android.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class king extends chessPiece{

    public king(int column, int row, chessColor color) {
        super(column, row, color, chessRank.KING, (color == chessColor.BLACK)? R.drawable.blackking: R.drawable.whiteking);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
//        Set<Pair<Integer, Integer>> legalMoves = new HashSet<>();
//        for (int cDif = -1; cDif <= 1; cDif++) {
//            for (int rDif = -1; rDif <= 1; rDif++) {
//                if (cDif == 0 && rDif == 0) {
//                    continue;
//                } else {
//
//                }
//            }
//        }
        return null;
    }

    @Override
    boolean validateMove(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
        return legalMoves.contains(new Pair(column, row));
    }

    @Override
    boolean move(int column, int row) {
        if (validateMove(column, row)) {
            this.column = column;
            this.row = row;
            return true;
        }
        return false;
    }

    @Override
    boolean canCheck(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
        return legalMoves.contains(new Pair(column, row));
    }
}
