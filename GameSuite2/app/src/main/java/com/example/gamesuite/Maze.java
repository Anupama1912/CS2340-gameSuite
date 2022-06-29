package com.example.gamesuite;

import java.io.Serializable;

public class Maze implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;

    private boolean[][] verticalLines;
    private boolean[][] horizontalLines;
    private int sizeX, sizeY;         //stores the width and height of the maze
    private int currentX, currentY;   //stores the current location of the ball
    private int finalX, finalY;       //stores the finishing point of the maze
    private boolean gameComplete;

    //setters and getters


    public int getFinalY() {
        return finalY;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getMazeWidth() {
        return finalX;
    }

    public int getMazeHeight() {
        return finalY;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public boolean[][] getHorizontalLines() {
        return horizontalLines;
    }

    public boolean[][] getVerticalLines() {
        return verticalLines;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    public void setFinalY(int finalY) {
        this.finalY = finalY;
    }

    public void setGameComplete(boolean gameComplete) {
        this.gameComplete = gameComplete;
    }

    public void setHorizontalLines(boolean[][] horizontalLines) {
        this.horizontalLines = horizontalLines;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setVerticalLines(boolean[][] verticalLines) {
        this.verticalLines = verticalLines;
    }

    public boolean move(int direction) {
        boolean moved = false;
        if(direction == UP) {
            if(currentY != 0 && !horizontalLines[currentY-1][currentX]) {
                currentY--;
                moved = true;
            }
        }
        if(direction == DOWN) {
            if(currentY != sizeY-1 && !horizontalLines[currentY][currentX]) {
                currentY++;
                moved = true;
            }
        }
        if(direction == RIGHT) {
            if(currentX != sizeX-1 && !verticalLines[currentY][currentX]) {
                currentX++;
                moved = true;
            }
        }
        if(direction == LEFT) {
            if(currentX != 0 && !verticalLines[currentY][currentX-1]) {
                currentX--;
                moved = true;
            }
        }
        if(moved) {
            if(currentX == finalX && currentY == finalY) {
                gameComplete = true;
            }
        }
        return moved;
    }


    public void setFinalPosition(int i, int i1) {
        setFinalX(i);
        setFinalY(i1);
    }

    public void setStartPosition(int i, int i1) {
        setCurrentX(i);
        setCurrentY(i1);
    }
}
