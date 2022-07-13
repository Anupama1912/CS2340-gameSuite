package com.example.gamesuite;

public class rook extends chessPiece{
    public rook(int column, int row, chessColor color) {
        super(column, row, color, chessRank.ROOK, (color == chessColor.BLACK)? R.drawable.blackrook: R.drawable.whiterook);
    }
}
