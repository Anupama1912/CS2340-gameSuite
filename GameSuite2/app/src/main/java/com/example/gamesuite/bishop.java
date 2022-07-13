package com.example.gamesuite;

public class bishop extends chessPiece{

    public bishop(int column, int row, chessColor color) {
        super(column, row, color, chessRank.BISHOP, (color == chessColor.BLACK)? R.drawable.blackbishop: R.drawable.whitebishop);
    }
}
