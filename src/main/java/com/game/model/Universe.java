package com.game.model;

import java.util.List;

public class Universe {
    private int horizontalSize;
    private int verticalSize;

    private Cell[][] grid;
    
    public Universe(Cell[][] grid) {
    	this.grid = grid;
    	horizontalSize = verticalSize = grid.length;
    }

    public Universe(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        grid = new Cell[verticalSize][horizontalSize];
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                grid[i][j] = new Cell(CellState.D);
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

    public int getAliveNeighbours(int x, int y, Cell[][] grid) {
        int result = 0;
        if (x != 0 && y != 0) {
            if (isAlive(x - 1, y - 1, grid)) {
                result++;
            }
        }

        if (x != 0) {
            if (isAlive(x - 1, y, grid)) {
                result++;
            }
        }

        if (x != 0 && y != grid.length - 1) {
            if (isAlive(x- 1, y + 1, grid)) {
                result++;
            }
        }
        if (y != 0) {
            if (isAlive(x, y - 1, grid)) {
                result++;
            }
        }
        //self
        if (y != grid.length - 1) {
            if (isAlive(x, y + 1, grid)) {
                result++;
            }
        }

        if (x != grid.length - 1 && y != 0) {
            if (isAlive(x + 1, y - 1, grid)) {
                result++;
            }
        }

        if (x != grid.length - 1) {
            if (isAlive(x + 1, y, grid)) {
                result++;
            }
        }

        if (x != grid.length - 1 && y != grid.length - 1) {
            if (isAlive(x + 1, y + 1, grid)) {
                result++;
            }
        }

        return result;
    }

    public static boolean isAlive(int row, int col, Cell[][] grid) {
        return grid[row][col].getState().equals(CellState.A);
    }

    public void changeState(List<CellCoordinates> cellList, Cell[][] grid) {
        cellList.forEach(cell -> {
            Cell currentCell = grid[cell.getX()][cell.getY()];
            if (currentCell.getState().equals(CellState.A)) {
                currentCell.setState(CellState.D);
                grid[cell.getX()][cell.getY()] = currentCell;
            } else {
                currentCell.setState(CellState.A);
                grid[cell.getX()][cell.getY()] = currentCell;
            }
        });
    }

    //, {"x":"1","y":"2"}, {"x":"1","y":"3"},{"x":"1","y":"4"}, {"x":"3","y":"10"}, {"x":"4","y":"10"}, {"x":"5","y":"10"}, {"x":"6","y":"10"}, {"x":"7","y":"10"}
}
