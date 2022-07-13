package com.example.gamesuite;

public class knight extends chessPiece{

    public knight(int column, int row, chessColor color) {
        super(column, row, color, chessRank.KNIGHT, (color == chessColor.BLACK)? R.drawable.blackknight: R.drawable.whiteknight);
    }
}
