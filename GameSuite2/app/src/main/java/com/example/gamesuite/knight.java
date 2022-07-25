package com.example.gamesuite;

import android.util.Log;
import android.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class knight extends chessPiece{

    public knight(int column, int row, chessColor color) {
        super(column, row, color, chessRank.KNIGHT, (color == chessColor.BLACK)? R.drawable.blackknight: R.drawable.whiteknight);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        Set<Pair<Integer, Integer>> legalMoves = new HashSet<>();
        HashMap<Pair<Integer, Integer>, chessPiece> board = new HashMap<>();
        Log.i("ksize", " " + legalMoves.size());
        for (chessPiece piece : chessActivity.boardPieces.values()) {
            board.put(new Pair<>(piece.column, piece.row), piece);
        }
        int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        Pair<Integer, Integer> king = chessActivity.teamPostion(this);
        board.remove(new Pair<>(this.column, this.row));
        for (int x = 0; x < 8; x++) {
            int col = column + Y[x];
            int row = this.row + X[x];
            if (col >= 0 && col <= 7 && row >= 0 && row <= 7) {
                chessPiece piece = chessBoard.pieceAt(col, row);
                if (piece == null || piece.color != this.color) {
                    Log.i("knight checkpos", "checing pos: " + col + "," + row);
                    board.put(new Pair<>(col, row), this);
                    boolean canCheck = kingInCheck(board, king.first, king.second);
                    if (piece != null) {
                        board.put(new Pair<>(piece.column, piece.row), piece);
                    }
                    if (!canCheck) {
                        legalMoves.add(new Pair<>(col, row));
                    }
                }
            }

        }
        board.put(new Pair<>(this.column, this.row), this);
        return legalMoves;
    }

    @Override
    boolean validateMove(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
        return legalMoves.contains(new Pair<>(column, row));
    }

    @Override
    boolean move(int column, int row) {
        if(validateMove(column, row)){
            chessActivity.setteamCheck(this, false);
            chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
            this.column = column;
            this.row = row;
            chessActivity.boardPieces.put(new Pair<>(column, row), this);
            Log.i("queen moving", "queen moveeed");
            Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
            Log.i("queen checked", "queen checked");
            if (legalMoves.contains(chessActivity.oppPostion(this))) {
                chessActivity.setOppCheck(this, true);
            }
            return true;
        }
        return false;
    }

    @Override
    boolean canCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row) {
        int colD = Math.abs(col - this.column);
        int rowD = Math.abs(row - this.row);
        if ((colD == 2 && rowD == 1) || (colD == 1 && rowD == 2)) {
            return true;
        }
        return false;
    }

    @Override
    boolean kingInCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row) {
        for (chessPiece piece: chessPieces.values()) {
            if (piece!= null && piece.color != this.color) {
                if (piece.canCheck(chessPieces, col, row)) {
                    return true;
                }
            }
        }
        return false;
    }

}
