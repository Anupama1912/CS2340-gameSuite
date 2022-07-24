package com.example.gamesuite;

public class tileMap {
    int tileNum;
    int livesCount = 3;
    int[][] currentmap;
    public tileMap(int tileNum) {
        this.tileNum = tileNum;
        if (tileNum == 1) {
            currentmap = map;
        }
        if (tileNum == 2) {
            currentmap = map2;
        }
    }
/*
    public void setTileNum(int tileNum) {
        this.tileNum = tileNum;
        if (tileNum == 1) {
            currentmap = map;
        }
        if (tileNum == 2) {
            currentmap = map2;
        }
    }
*/
    public int getLivesCount() {
        return livesCount;
    }

    public void setLivesCount(int livesCount) {
        this.livesCount = livesCount;
    }

    public int getTileNum() {
        return tileNum;
    }

    int[][] map = new int[][]{
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,1},
            {1,2,2,2,5,5,5,2,2,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,2,5,5,5,2,3,5,5,5,1},
            {1,5,5,5,5,5,5,5,2,5,5,5,2,2,2,2,2,1},
            {1,5,5,5,5,5,5,5,2,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,2,2,2,2,5,1},
            {1,5,5,5,3,2,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,5,2,2,2,2,2,2,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,2,2,2,1},
            {1,5,2,2,2,2,2,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,3,5,5,2,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,2,2,2,2,2,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    int[][] map2 = new int[][]{
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,1},
            {1,2,2,2,5,5,5,2,2,5,5,5,5,5,5,5,5,1},
            {1,2,5,5,5,5,5,5,2,5,5,5,2,3,5,5,5,1},
            {1,5,2,5,5,5,5,5,2,5,5,5,2,2,2,2,2,1},
            {1,5,2,2,2,5,5,5,2,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,2,2,2,2,5,5,5,5,5,5,2,2,2,2,5,1},
            {1,5,5,5,3,2,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,5,5,2,2,2,2,2,2,5,5,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,2,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,2,5,5,5,2,2,2,1},
            {1,5,2,2,2,2,2,5,5,5,2,5,5,5,5,5,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,3,5,5,2,5,1},
            {1,5,5,5,5,5,5,5,5,5,5,2,2,2,2,2,5,1},
            {1,5,2,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

//    int[][] map2 = new int[][]{
//            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,4,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
//    };

}
