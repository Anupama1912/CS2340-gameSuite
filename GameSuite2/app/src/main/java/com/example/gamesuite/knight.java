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
            return true;
        }
        return false;
    }

    @Override
    boolean canCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row) {
        return false;
    }

    @Override
    boolean kingInCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row) {
        return false;
    }


}
