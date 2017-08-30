package com.game.model;

public class Universe {
    private int horizontalSize;
    private int verticalSize;

    private Cell[][] grid;

    public Universe(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        grid = new Cell[verticalSize][horizontalSize];
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                grid[i][j] = Cell.D;
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }
}
