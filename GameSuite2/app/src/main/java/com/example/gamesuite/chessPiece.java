package com.example.gamesuite;

import android.util.Pair;

import java.util.HashMap;
import java.util.Set;

public abstract class chessPiece {
    int column;
    int row;
    chessColor color;
    chessRank rank;
    int id;
    int moves;

    public chessPiece(int column, int row, chessColor color, chessRank rank, int id) {
        this.column = column;
        this.row = row;
        this.color = color;
        this.rank = rank;
        this.id = id;
        moves = 0;
    }

    /**
     *  Returns possible moves of this piece
     * @return A list of pairs representing cells the piece can move to.
     */
    abstract Set<Pair<Integer, Integer>> getLegalMovements();

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

    /**
     *
     * @param chessPieces a Map which is a COPY of the original map (this can be a map of a
     *                    hypothetical move or just a copy of the original hashmap)
     * @return whether the piece can put opposing king at check
     */
    abstract boolean canCheck(HashMap<Pair<Integer, Integer>,chessPiece> chessPieces, int col, int row);

    /**
     * Returns whether the current pieces king is in check in a map of pieces representing
     * a hypothetical move
     * @param chessPieces a Map which is a COPY of the original map, with a hypothetical move the
     *                   piece wants to make already made
     * @return whether or not the king would be in check if this move where to happen
     */
    boolean kingInCheck(HashMap<Pair<Integer, Integer>,chessPiece> chessPieces){
        return false;
    }
}
