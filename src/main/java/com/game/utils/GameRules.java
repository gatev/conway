package com.game.utils;

import com.game.model.Cell;
import com.game.model.CellCoordinates;
import com.game.model.CellState;
import com.game.model.Universe;

import java.util.List;

public class GameRules {

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
}
