package com.example.gamesuite;

public class chessPiece {
    int column;
    int row;
    chessColor color;
    chessRank rank;

    public chessPiece(int column, int row, chessColor color, chessRank rank) {
        this.column = column;
        this.row = row;
        this.color = color;
        this.rank = rank;
    }
}
