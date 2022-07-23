package com.example.gamesuite;

import android.util.Pair;

<<<<<<< HEAD
import java.util.HashSet;
import java.util.Map;
=======
import java.util.HashMap;
>>>>>>> 101761e3bdeca049997bbbe8f464a655ef2aeaf9
import java.util.Set;

public class pawn extends chessPiece{
    int numMoves = 0;
    int taking = 0; //0 moving to an empty, 1 taking piece, 2 en passant
    public pawn(int column, int row, chessColor color) {
        super(column, row, color, chessRank.PAWN, (color == chessColor.BLACK)? R.drawable.blackpawn: R.drawable.whitepawn);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        return getLegalMovements(false);
    }

    Set<Pair<Integer, Integer>> getLegalMovements(boolean king) {
        Map<Pair<Integer, Integer>, chessPiece> chessPieces = chessActivity.boardPieces;
        Set<Pair<Integer, Integer>> legalMovements = new HashSet<Pair<Integer, Integer>>();
        int color = 1;
        chessColor opposite = chessColor.BLACK;
        if(this.color == chessColor.BLACK) {
            color = -1;
            opposite = chessColor.WHITE;
        }
        if(column < 8) {
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column+1, row + color))
                    && (chessPieces.get(new Pair<Integer, Integer>(column + 1, row + color)).color == opposite || king)){
                legalMovements.add(new Pair<Integer, Integer>(column + 1, row + color));
            }
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column+1, row))) {
                chessPiece p = chessPieces.get(new Pair<Integer, Integer>(column + 1, row));
                if(p.color == opposite && pawn.class.isInstance(p) && ((pawn) p).numMoves == 1){
                    legalMovements.add((new Pair<Integer, Integer>(column + 1, row + color)));
                }
            }
        }
        if(column > 0) {
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column-1, row + color))
                    && (chessPieces.get(new Pair<Integer, Integer>(column-1, row + color)).color == opposite || king)){
                legalMovements.add(new Pair<Integer, Integer>(column-1, row + color));
            }
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column-1, row))) {
                chessPiece p = chessPieces.get(new Pair<Integer, Integer>(column + 1, row));
                if(p.color == opposite && p instanceof pawn && ((pawn) p).numMoves == 1){
                    legalMovements.add((new Pair<Integer, Integer>(column - 1, row + color)));
                }
            }
        }
        if(!chessPieces.containsKey(new Pair<Integer, Integer>(column, row + 1)) && !king){
            legalMovements.add(new Pair<Integer, Integer>(column, row + 1));
        }
        if(numMoves == 0 && !chessPieces.containsKey(new Pair<Integer, Integer>(column, row + 2)) && !king){
            legalMovements.add(new Pair<Integer, Integer>(column, row + 2));
        }
        return legalMovements;
    }

    @Override
    boolean validateMove(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements();
        return legalMoves.contains(new Pair<Integer, Integer>(column, row));
    }

    @Override
    boolean move(int column, int row) {
        int color = 1;
        chessColor opposite = chessColor.BLACK;
        if(this.color == chessColor.BLACK) {
            color = -1;
            opposite = chessColor.WHITE;
        }
        if(validateMove(column, row)) {
            if(this.column == column + 1 || this.column == column - 1) {
                if(chessActivity.boardPieces.get(new Pair<Integer, Integer>(column, row)) != null){
                    this.taking = 1;
                    chessActivity.boardPieces.remove(new Pair<Integer, Integer>(column, row));
                } else {
                    this.taking = 2;
                    chessActivity.boardPieces.remove(new Pair<Integer, Integer>(column, row-1));
                }
            } else {
                this.taking = 0;
            }

            chessActivity.boardPieces.remove(new Pair<Integer, Integer>(this.column, this.row));
            this.column = column;
            this.row = row;
            chessActivity.boardPieces.put(new Pair<Integer, Integer>(this.column, this.row), this);
            return true;
        }
        return false;
    }

    @Override
<<<<<<< HEAD
    boolean canCheck(int column, int row) {
        Set<Pair<Integer, Integer>> legalMoves = getLegalMovements(true);
        if(legalMoves.contains(new Pair<Integer, Integer>(column, row))){
            return true;
        }
=======
    boolean canCheck(HashMap<Pair<Integer, Integer>,chessPiece> chessPieces) {
>>>>>>> 101761e3bdeca049997bbbe8f464a655ef2aeaf9
        return false;
    }
}
