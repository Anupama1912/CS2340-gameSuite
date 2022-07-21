package com.example.gamesuite;

import android.util.Pair;

import java.util.List;

public abstract class chessPiece {
    int column;
    int row;
    chessColor color;
    chessRank rank;
    int id;

    public chessPiece(int column, int row, chessColor color, chessRank rank, int id) {
        this.column = column;
        this.row = row;
        this.color = color;
        this.rank = rank;
        this.id = id;
    }

    /**
     *  Returns possible moves of this piece
     * @return A list of pairs representing cells the piece can move to.
     */
    abstract List<Pair<Integer, Integer>> getLegalMovements();

    /**
     * Checks move legality
     * @param column the column of the cell we're attempting to move to
     * @param row the row of the cell we're attempting to move to
     * @return whether the move is legal or not
     */
    abstract boolean validateMove(int column, int row);

    /**
     *
     * @param column the column of the cell to move to
     * @param row the row of the cell to move to
     * @return whether the move was successful
     */
    abstract boolean move(int column, int row);
}
