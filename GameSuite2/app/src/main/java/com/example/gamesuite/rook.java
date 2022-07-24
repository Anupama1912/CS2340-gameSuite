package com.example.gamesuite;

import android.util.Log;
import android.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class rook extends chessPiece{
    public rook(int column, int row, chessColor color) {
        super(column, row, color, chessRank.ROOK, (color == chessColor.BLACK)? R.drawable.blackrook: R.drawable.whiterook);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        Set<Pair<Integer, Integer>> legalMoves = new HashSet<>();
        helper(1,0, legalMoves);
        helper(0,1,legalMoves);
        helper(-1,0, legalMoves);
        helper(0,-1,legalMoves);
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
            Log.i("rook moving", "rook moveeed");
            Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
            Log.i("rook checked", "rook checked");
            if (legalMoves.contains(chessActivity.oppPostion(this))) {
                chessActivity.setOppCheck(this, true);
            }
            return true;
        }
        return false;
    }

    @Override
    boolean canCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row1) {
        if (!(col == this.column || row1 == this.row)) {
            return false;
        }
        int rowIncre;
        int colIncre;
        if (this.row == row1) {
            rowIncre = 0;
        } else {
            if (row1 > this.row) {
                rowIncre = 1;
            } else {
                rowIncre = -1;
            }
        }
        if (this.column == col) {
             colIncre = 0;
        } else {
            if (col > this.column) {
                colIncre = 1;
            } else {
                colIncre = -1;
            }
        }
        int column = this.column + colIncre;
        int row = this.row + rowIncre;
        chessPiece piece = chessPieces.get(new Pair<>(column, row));
        if (column == col && row == row1 || (piece != null  && piece.color != this.color)) {
            return true;
        }
        while ((piece == null || piece.color != this.color) && (column != col || row != row1)) {
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
