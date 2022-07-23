package com.example.gamesuite;

import android.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class queen extends chessPiece{
    boolean inCheck;
    chessColor oppColor;
    boolean oppInCheck;
    int oppColumn;
    int oppRow;
    public queen(int column, int row, chessColor color) {
        super(column, row, color, chessRank.QUEEN, (color == chessColor.BLACK)? R.drawable.blackqueen: R.drawable.whitequeen);
        if(color == chessColor.BLACK) {
            inCheck = chessActivity.blackInCheck;
            oppInCheck = chessActivity.whiteInCheck;
            oppColor = chessColor.WHITE;
            oppColumn = chessActivity.whiteColumn;
            oppRow = chessActivity.whiteRow;
        } else {
            inCheck = chessActivity.whiteInCheck;
            oppInCheck = chessActivity.blackInCheck;
            oppColor = chessColor.BLACK;
            oppColumn = chessActivity.blackColumn;
            oppRow = chessActivity.blackRow;
        }
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

    void helper(int columnIncre, int rowIncre,Set<Pair<Integer, Integer>> legalMoves ) {
        int column = this.column + columnIncre;
        int row = this.row + rowIncre;
        while (chessBoard.pieceAt(column, row) == null || chessBoard.pieceAt(column, row).color != this.color) {
            if (column < 0 || column > 7) {
                break;
            }
            if (row < 0 || row > 7) {
                break;
            }
            boolean pieceExist = chessBoard.pieceAt(column, row) == null;
            if (chessActivity.inCheck) {
                boolean prevIsNull = false;
                chessPiece prev = chessBoard.pieceAt(column, row);
                if (prev == null) {
                    prevIsNull = true;
                }
                boolean check = false;
                chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
                chessActivity.boardPieces.put(new Pair<>(column, row), new queen(column, row, this.color));
                for (chessPiece enemy: chessActivity.boardPieces.values()) {
                    if (enemy == null) {
                        continue;
                    } else {
                        if (enemy.color != this.color) {
                            if (enemy.canCheck(column, row)) {
                                check = true;
                                break;
                            }
                        }
                    }
                }
                if(!check) {
                    legalMoves.add(new Pair<>(column, row));
                }
                chessActivity.boardPieces.remove(new Pair<>(column, row));
                chessActivity.boardPieces.put(new Pair<>(this.column, this.row), this);
                if (!prevIsNull) {
                    chessActivity.boardPieces.put(new Pair<>(column, row),prev);
                }
            } else {
                legalMoves.add(new Pair<>(column, row));
                if(!pieceExist) {
                    break;
                }
            }
            column += columnIncre;
            row += rowIncre;
        }
    }



    @Override
    boolean validateMove(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
        return legalMoves.contains(new Pair<>(column, row));
    }

    @Override
    boolean move(int column, int row) {
        if(validateMove(column, row)){
            chessActivity.inCheck = false;
            chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
            chessActivity.boardPieces.put(new Pair<>(column, row), this);
            this.column = column;
            this.row = row;
            Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
            if (legalMoves.contains(new Pair<>(oppColumn, oppRow))) {
                oppInCheck = true;
            }
            if (oppColor == chessColor.BLACK) {
                chessActivity.blackInCheck = oppInCheck;
            } else {
                chessActivity.whiteInCheck = oppInCheck;
            }
            return true;
        }
        return false;
    }

    @Override
    boolean canCheck(int column, int row) {
        if (chessBoard.pieceAt(column, row) == null || chessBoard.pieceAt(column, row).color == this.color) {
            chessPiece prev = chessBoard.pieceAt(column, row);
            boolean prevEmpty = prev == null;
            chessActivity.boardPieces.remove(new Pair<>(column, row));
            Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
            if (!prevEmpty) {
                chessActivity.boardPieces.put(new Pair<>(column, row), prev);
            }
            return legalMoves.contains(new Pair<>(column, row));
        }
        return false;
    }
}
