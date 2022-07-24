package com.example.gamesuite;

import android.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class pawn extends chessPiece{
    public pawn(int column, int row, chessColor color) {
        super(column, row, color, chessRank.PAWN, (color == chessColor.BLACK)? R.drawable.blackpawn: R.drawable.whitepawn);
    }

    @Override
    Set<Pair<Integer, Integer>> getLegalMovements() {
        Map<Pair<Integer, Integer>, chessPiece> chessPieces = chessActivity.boardPieces;
        Set<Pair<Integer, Integer>> legalMovements = new HashSet<Pair<Integer, Integer>>();

        int color = 1;
        chessColor opposite = chessColor.BLACK;
        if(this.color == chessColor.BLACK) {
            color = -1;
            opposite = chessColor.WHITE;
        }
        if(column < 7) {
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column+1, row + color))
                    && (chessPieces.get(new Pair<Integer, Integer>(column + 1, row + color)).color == opposite)){
                HashMap<Pair<Integer, Integer>, chessPiece> newMap = new HashMap<Pair<Integer, Integer>, chessPiece>(chessPieces);
                moveHelper(newMap, column + 1, row + color, this.column, this.row);
                if(!kingInCheck(newMap, chessActivity.teamPostion(this).first, chessActivity.teamPostion(this).second)) {
                    legalMovements.add(new Pair<Integer, Integer>(column + 1, row + color));
                }
            }
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column+1, row))) {
                chessPiece p = chessPieces.get(new Pair<Integer, Integer>(column + 1, row));
                if(p.color == opposite && p instanceof pawn && p.moves == 1){
                    HashMap<Pair<Integer, Integer>, chessPiece> newMap = new HashMap<Pair<Integer, Integer>, chessPiece>(chessPieces);
                    moveHelper(newMap, column + 1, row + color, this.column, this.row);
                    if(!kingInCheck(newMap, chessActivity.teamPostion(this).first, chessActivity.teamPostion(this).second)) {
                        legalMovements.add(new Pair<Integer, Integer>(column + 1, row + color));
                    }
                    legalMovements.add((new Pair<Integer, Integer>(column + 1, row + color)));
                }
            }
        }
        if(column > 0) {
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column-1, row + color))
                    && (chessPieces.get(new Pair<Integer, Integer>(column-1, row + color)).color == opposite)){
                HashMap<Pair<Integer, Integer>, chessPiece> newMap = new HashMap<Pair<Integer, Integer>, chessPiece>(chessPieces);
                moveHelper(newMap, column - 1, row + color, this.column, this.row);
                if(!kingInCheck(newMap, chessActivity.teamPostion(this).first, chessActivity.teamPostion(this).second)) {
                    legalMovements.add(new Pair<Integer, Integer>(column - 1, row + color));
                }
            }
            if(chessPieces.containsKey(new Pair<Integer, Integer>(column-1, row))) {
                chessPiece p = chessPieces.get(new Pair<Integer, Integer>(column - 1, row));
                if(p.color == opposite && p instanceof pawn && ((pawn) p).moves == 1){
                    HashMap<Pair<Integer, Integer>, chessPiece> newMap = new HashMap<Pair<Integer, Integer>, chessPiece>(chessPieces);
                    moveHelper(newMap, column - 1, row + color, this.column, this.row);
                    if(!kingInCheck(newMap, chessActivity.teamPostion(this).first, chessActivity.teamPostion(this).second)) {
                        legalMovements.add(new Pair<Integer, Integer>(column - 1, row + color));
                    }
                }
            }
        }
        if(!chessPieces.containsKey(new Pair<Integer, Integer>(column, row + color))){
            HashMap<Pair<Integer, Integer>, chessPiece> newMap = new HashMap<Pair<Integer, Integer>, chessPiece>(chessPieces);
            moveHelper(newMap, column, row + color, this.column, this.row);
            if(!kingInCheck(newMap, chessActivity.teamPostion(this).first, chessActivity.teamPostion(this).second)) {
                legalMovements.add(new Pair<Integer, Integer>(column, row + color));
            }
        }
        if(moves == 0 && !chessPieces.containsKey(new Pair<Integer, Integer>(column, row + 2*color))){
            HashMap<Pair<Integer, Integer>, chessPiece> newMap = new HashMap<Pair<Integer, Integer>, chessPiece>(chessPieces);
            moveHelper(newMap, column, row + 2*color, this.column, this.row);
            if(!kingInCheck(newMap, chessActivity.teamPostion(this).first, chessActivity.teamPostion(this).second)) {
                legalMovements.add(new Pair<Integer, Integer>(column, row + 2*color));
            }
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
//        if (validateMove(column, row)) {
//            chessActivity.boardPieces.put(new Pair<>(column, row), this);
//            chessActivity.boardPieces.remove(new Pair<>(this.column, this.row));
//            this.column = column;
//            this.row = row;
//            moves++;
//            return true;
//        }
//        return false;
        System.out.println("Moving pawn to: " + column + ", " + row);
        //if(validateMove(column, row)){
        if(true){
            System.out.println("Move Validated");
            moveHelper(chessActivity.boardPieces, column, row, this.column, this.row);
            this.column = column;
            this.row = row;
            moves++;
            return true;
        }
        return false;
    }

    void moveHelper(Map<Pair<Integer, Integer>, chessPiece> pieces, int column, int row, int currCol, int currRow){
        if(currCol == column + 1 || currCol == column - 1) {
            if(pieces.get(new Pair<Integer, Integer>(column, row)) != null){
                //this.taking = 1;
                pieces.remove(new Pair<Integer, Integer>(column, row));
            } else {
                //this.taking = 2;
                pieces.remove(new Pair<Integer, Integer>(column, row-1));
            }
        }
        pieces.remove(new Pair<Integer, Integer>(currCol, currRow));
        pieces.put(new Pair<Integer, Integer>(column, row), this);
    }

    @Override
    boolean canCheck(HashMap<Pair<Integer, Integer>,chessPiece> chessPieces, int col, int row) {
        int color = 1;
        if(this.color == chessColor.BLACK){color = -1;}
        if(column < 7) {
            if(chessPieces.containsKey(new Pair<Integer, Integer>(this.column+1, this.row + color))){
                chessPiece b = chessPieces.get(new Pair<Integer, Integer>(column + 1, row + color));
                if(b.color != this.color && b instanceof king){
                    return true;
                }
            }
        }
        if(column > 0) {
            if(chessPieces.containsKey(new Pair<Integer, Integer>(this.column-1, this.row + color))){
                chessPiece b = chessPieces.get(new Pair<Integer, Integer>(column-1, row + color));
                if(b.color != this.color && b instanceof king){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    boolean kingInCheck(HashMap<Pair<Integer, Integer>, chessPiece> chessPieces, int col, int row) {
        for (chessPiece piece: chessPieces.values()) {
            if (piece!= null && piece.color != this.color) {
                HashMap<Pair<Integer, Integer>, chessPiece> p = new HashMap<Pair<Integer, Integer>, chessPiece>(chessPieces);
                if (piece.canCheck(p, col, row)) {
                    return true;
                }
            }
        }
        return false;
    }
}
