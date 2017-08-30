package com.game.utils;

import com.game.model.Cell;
import com.game.model.CellCoordinates;
import com.game.model.Universe;

import java.util.List;

public class GameRules {

    public static int getAliveNeighbours(int x, int y, Universe universe) {
        int result = 0;
        if (x != 0 && y != 0) {
            if (isAlive(x - 1, y - 1, universe)) {
                result++;
            }
        }

        if (x != 0) {
            if (isAlive(x - 1, y, universe)) {
                result++;
            }
        }

        if (x != 0 && y != universe.getHorizontalSize() - 1) {
            if (isAlive(x- 1, y + 1, universe)) {
                result++;
            }
        }
        if (y != 0) {
            if (isAlive(x, y - 1, universe)) {
                result++;
            }
        }
        //self
        if (y != universe.getHorizontalSize() - 1) {
            if (isAlive(x, y + 1, universe)) {
                result++;
            }
        }

        if (x != universe.getVerticalSize() - 1 && y != 0) {
            if (isAlive(x + 1, y - 1, universe)) {
                result++;
            }
        }

        if (x != universe.getVerticalSize() - 1) {
            if (isAlive(x + 1, y, universe)) {
                result++;
            }
        }

        if (x != universe.getVerticalSize() - 1 && y != universe.getHorizontalSize() - 1) {
            if (isAlive(x + 1, y + 1, universe)) {
                result++;
            }
        }

        return result;
    }

    public static boolean shouldChangeState(Cell cell, int aliveNeighbours) {
        switch (cell.getState()) {
            case D:
                if (aliveNeighbours == 3) {
                    return true;
                } else {
                    return false;
                }
            case A:
                if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                    return true;
                } else {
                    return false;
                }
            default:
                throw new IllegalArgumentException();
        }

    }

    public static void changeState(List<CellCoordinates> cellList, Universe universe) {
        cellList.forEach(cell -> {
            Cell currentCell = universe.getGrid()[cell.getX()][cell.getY()];
            if (currentCell.getState().equals(Cell.State.A)) {
                universe.getGrid()[cell.getX()][cell.getY()] = Cell.D;
            } else {
                universe.getGrid()[cell.getX()][cell.getY()] = Cell.A;
            }
        });
    }

    public static boolean isAlive(int row, int col, Universe universe) {
        return universe.getGrid()[row][col].getState().equals(Cell.State.A);
    }

}
