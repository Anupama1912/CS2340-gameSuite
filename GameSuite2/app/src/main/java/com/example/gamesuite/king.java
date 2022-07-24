package com.example.gamesuite;

import android.util.Log;
import android.util.Pair;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class king extends chessPiece{
    public king(int column, int row, chessColor color) {
        super(column, row, color, chessRank.KING, (color == chessColor.BLACK)? R.drawable.blackking: R.drawable.whiteking);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        Set<Pair<Integer, Integer>> legalMoves = new HashSet<>();
        HashMap<Pair<Integer, Integer>, chessPiece> board = new HashMap<>();
        Log.i("ksize", " " + legalMoves.size());
        for (chessPiece piece : chessActivity.boardPieces.values()) {
            board.put(new Pair<>(piece.column, piece.row), piece);
        }

        for (int cDif = -1; cDif <= 1; cDif++) {
            for (int rDif = -1; rDif <= 1; rDif++) {
                if (cDif == 0 && rDif == 0) {
                    continue;
                } else {
                    int fcolumn = column + cDif;
                    int frow = row + rDif;
                    if (fcolumn >= 0 && fcolumn <= 7 && frow >= 0 && frow <= 7) {
                        chessPiece piece = chessBoard.pieceAt(fcolumn, frow);
                        if (piece == null || piece.color != this.color) {
                            board.remove(new Pair<>(this.column, this.row));
                            board.put(new Pair<>(fcolumn, frow), this);
                            boolean canCheck = kingInCheck(board, fcolumn, frow);
                            if (piece != null) {
                                board.put(new Pair<>(piece.column, piece.row), piece);
                            }
                            if (!canCheck) {
                                legalMoves.add(new Pair<>(column + cDif, row + rDif));
                            }
                        }
                    }
                }
            }
        }
        if (!chessActivity.inTeamCheck(this)) {
            if (moves == 0) {
                chessPiece right = chessBoard.pieceAt(7, row);
                chessPiece left = chessBoard.pieceAt(0, row);
                if (left != null) {
                    if (left.rank == chessRank.ROOK && left.moves == 0) {
                        if (chessBoard.pieceAt(3, row) == null && chessBoard.pieceAt(2, row) == null) {
                            board.put(new Pair<>(3, row), this);
                            boolean canCheck1 = kingInCheck(board, 3, row);
                            board.remove(new Pair<>(3, row));
                            board.put(new Pair<>(2, row), this);
                            boolean canCheck = canCheck1 && kingInCheck(board, 2, row);
                            board.remove(new Pair<>(2, row));
                            if (!canCheck) {
                                legalMoves.add(new Pair<>(2, row));
                            }
                        }
                    }
                }
                if (right != null) {
                    if (right.rank == chessRank.ROOK && right.moves == 0) {
                        if (chessBoard.pieceAt(5, row) == null && chessBoard.pieceAt(6, row) == null) {
                            board.put(new Pair<>(5, row), this);
                            boolean canCheck1 = kingInCheck(board, 5, row);
                            board.remove(new Pair<>(5, row));
                            board.put(new Pair<>(6, row), this);
                            boolean canCheck = canCheck1 && kingInCheck(board, 6, row);
                            board.remove(new Pair<>(6, row));
                            if (!canCheck) {
                                legalMoves.add(new Pair<>(6, row));
                            }
                        }
                    }
                }
            }
        }
        board.put(new Pair<>(this.column, this.row), this);
        Log.i("ksize", " " + legalMoves.size());
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
            chessActivity.setteamCheck(this, false);
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
            this.column = column;
            this.row = row;
            chessActivity.boardPieces.put(new Pair<>(column, row), this);
            chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
            if (color == chessColor.BLACK) {
                chessActivity.bKingCol = column;
                chessActivity.bKingRow = row;
            } else {
                chessActivity.wKingCol = column;
                chessActivity.wKingRow = row;
            }
            moves++;
            return true;
        }
        return false;
    }

    @Override
    boolean canCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row) {
        int colD = Math.abs(col - column);
        int rowD = Math.abs(row - this.row);
        return (colD == 1 || colD == 0) && (rowD == 1 || rowD == 0);
    }


    boolean kingInCheck(HashMap<Pair<Integer, Integer>,chessPiece> chessPieces, int col, int row){
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
