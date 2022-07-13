package com.example.gamesuite;

public class pawn extends chessPiece{
    public pawn(int column, int row, chessColor color) {
        super(column, row, color, chessRank.PAWN, (color == chessColor.BLACK)? R.drawable.blackpawn: R.drawable.whitepawn);
    }
}
