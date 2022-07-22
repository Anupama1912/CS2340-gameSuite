package com.example.gamesuite;

import android.util.Pair;

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
        return true;
    }

    @Override
    boolean move(int column, int row) {
        if (validateMove(column, row)) {
            chessActivity.boardPieces.put(new Pair<>(column, row), this);
            chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
            this.column = column;
            this.row = row;
            moves++;
        }
        return true;
    }

    @Override
    boolean canCheck(int column, int row) {
        return false;
    }
}
