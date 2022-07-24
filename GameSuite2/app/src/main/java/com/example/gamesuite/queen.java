package com.example.gamesuite;

import android.util.Log;
import android.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class queen extends chessPiece{
    static boolean bool = false;
    public queen(int column, int row, chessColor color) {
        super(column, row, color, chessRank.QUEEN, (color == chessColor.BLACK)? R.drawable.blackqueen: R.drawable.whitequeen);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        Set<Pair<Integer, Integer>> legalMoves = new HashSet<>();
        for (int cDif = -1; cDif <= 1; cDif++) {
            for (int rDif = -1; rDif <= 1; rDif++) {
                if (cDif == 0 && rDif == 0) {
                    continue;
                } else {
                    helper(cDif, rDif, legalMoves);
                }
            }
        }
        return legalMoves;
    }

    void helper(int columnIncre, int rowIncre, Set<Pair<Integer, Integer>> legalMoves) {
        HashMap<Pair<Integer, Integer>, chessPiece> board = new HashMap<>();
        for (chessPiece piece: chessActivity.boardPieces.values()) {
            board.put(new Pair<>(piece.column, piece.row), piece);
        }
        if (chessActivity.inTeamCheck(this)) {
            if (color == chessColor.BLACK) {
                Log.i("King position", "in check black");
                String bool = chessActivity.inoppCheck(this) == true? "white check" : "white no check";
                Log.i("king pos", bool);
            } else {
                Log.i("King position", "in check white");
            }
        }
        int column = this.column + columnIncre;
        int row = this.row + rowIncre;
        board.remove(new Pair<>(this.column, this.row));
        while (chessBoard.pieceAt(column, row) == null || chessBoard.pieceAt(column, row).color != this.color) {
            chessPiece piece = chessBoard.pieceAt(column, row);
            if (column < 0 || column > 7) {
                break;
            }
            if (row < 0 || row > 7) {
                break;
            }
            board.put(new Pair<>(column, row), this);
            Pair<Integer, Integer> king = chessActivity.teamPostion(this);
            Log.i("kpos", "" + king.first + "," + king.second);
            if (!kingInCheck(board, king.first, king.second)) {
                legalMoves.add(new Pair<>(column, row));
            }
            board.remove(new Pair<>(column, row));
            if (piece != null) {
                board.put(new Pair<>(column, row), piece);
                break;
            }
            column += columnIncre;
            row += rowIncre;
        }
        board.put(new Pair<>(this.column, this.row), this);
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
            chessActivity.boardPieces.put(new Pair<>(column, row), this);
            chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
            this.column = column;
            this.row = row;
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
    boolean canCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row1) {
        int colD = col - column;
        int rowD = row1 - this.row;
        if ((colD == rowD && colD != 0 && rowD != 0) || (colD == 0) || rowD == 0) {
            int colIncre;
            int rowIncre;
            if (colD == 0) {
                colIncre = 0;
            } else if (colD > 0) {
                colIncre = 1;
            } else {
                colIncre = -1;
            }
            if (rowD == 0) {
                rowIncre = 0;
            } else if (rowD > 0) {
                rowIncre = 1;
            } else {
                rowIncre = -1;
            }
            int column = this.column + colD;
            int row = this.row + rowIncre;
            chessPiece piece = chessPieces.get(new Pair<>(column, row));
            if (col == column && row == row1 && (piece == null || piece.color != this.color)) {
                return true;
            }
            while ((piece == null || piece.color != this.color) && (column != col && row != row1)) {
                if (piece == null) {
                    column += colIncre;
                    row += rowIncre;
                    piece = chessPieces.get(new Pair<>(column, row));
                } else {
                    return false;
                }
            }
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
