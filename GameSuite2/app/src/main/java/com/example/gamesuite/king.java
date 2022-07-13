package com.example.gamesuite;

public class king extends chessPiece{

    public king(int column, int row, chessColor color) {
        super(column, row, color, chessRank.KING, (color == chessColor.BLACK)? R.drawable.blackking: R.drawable.whiteking);
    }
}
