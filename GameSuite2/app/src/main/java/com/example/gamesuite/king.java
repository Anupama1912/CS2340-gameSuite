package com.example.gamesuite;

import android.util.Pair;


import java.util.HashSet;
import java.util.Set;

public class king extends chessPiece{

    public king(int column, int row, chessColor color) {
        super(column, row, color, chessRank.KING, (color == chessColor.BLACK)? R.drawable.blackking: R.drawable.whiteking);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        Set<Pair<Integer, Integer>> legalMoves = new HashSet<>();
        for (int cDif = -1; cDif <= 1; cDif++) {
            for (int rDif = -1; rDif <= 1; rDif++) {
                if (cDif == 0 && rDif == 0) {
                    continue;
                } else {
                    int fcolumn = column + cDif;
                    int frow = row + rDif;
                    if (fcolumn >= 0 && fcolumn <= 7 && frow >= 0 && frow <= 7) {
                        chessPiece piece = chessBoard.pieceAt(column + cDif, row + rDif);
                        if (piece == null || piece.color != this.color) {
                            boolean canCheck = checkDetector(column + cDif, row + rDif);
                            if (!canCheck) {
                                legalMoves.add(new Pair<>(column + cDif, row + rDif));
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        if (!chessActivity.inCheck) {
            if (moves == 0) {
                chessPiece right = chessBoard.pieceAt(7, row);
                chessPiece left = chessBoard.pieceAt(0, row);
                if (left != null) {
                    if (left.rank == chessRank.ROOK && left.moves == 0) {
                        if (chessBoard.pieceAt(3, row) == null && chessBoard.pieceAt(2, row) == null) {
                            boolean canCheck = checkDetector(3, row) && checkDetector(2, row);
                            if (!canCheck) {
                                legalMoves.add(new Pair<>(2, row));
                            }
                        }
                    }
                }
                if (right != null) {
                    if (right.rank == chessRank.ROOK && right.moves == 0) {
                        if (chessBoard.pieceAt(5, row) == null && chessBoard.pieceAt(6, row) == null) {
                            boolean canCheck = checkDetector(5, row) && checkDetector(6, row);
                            if (!canCheck) {
                                legalMoves.add(new Pair<>(6, row));
                            }
                        }

                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    boolean validateMove(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
        return legalMoves.contains(new Pair<>(column, row));
    }

    @Override
    boolean move(int column, int row) {
        if (validateMove(column, row)) {
            int distance = column - this.column;
            if (Math.abs(distance) == 2) {
                if (distance > 0) {
                    chessPiece rook = chessBoard.pieceAt(7, this.row);
                    chessActivity.boardPieces.remove(new Pair<>(7, this.row));
                    rook.column = 5;
                    rook.moves++;
                    chessActivity.boardPieces.put(new Pair<>(5, this.row), rook);
                } else {
                    chessPiece rook = chessBoard.pieceAt(0, this.row);
                    chessActivity.boardPieces.remove(new Pair<>(0, this.row));
                    rook.column = 3;
                    rook.moves++;
                    chessActivity.boardPieces.put(new Pair<>(3, this.row), rook);
                }
            }
            chessActivity.boardPieces.put(new Pair<>(column, row), this);
            chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
            this.column = column;
            this.row = row;
            moves++;
            return true;
        }
        return false;
    }

    @Override
    boolean canCheck(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
        return legalMoves.contains(new Pair(column, row));
    }

    boolean checkDetector(int column, int row) {
        for (chessPiece enemy: chessActivity.boardPieces.values()) {
            if (enemy == null) {
                continue;
            } else {
                if (enemy.color != this.color) {
                    if (enemy.canCheck(column, row)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}