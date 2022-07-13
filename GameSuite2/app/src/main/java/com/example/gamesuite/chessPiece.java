package com.example.gamesuite;

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
}
