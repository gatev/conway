package com.game.services;

import com.game.model.*;
import com.game.utils.GameRules;

import java.util.ArrayList;
import java.util.List;

public class GameManagerServiceImpl implements GameManagerService {

    @Override
    public List<CellCoordinates> playGame(InitialConfig initialConfig) {
        final Universe universe = new Universe(initialConfig.getHorizontalSize(), initialConfig.getVerticalSize());
        initialConfig.getListAliveCells().forEach((aliveCellCoordinate) ->
                universe.getGrid()[aliveCellCoordinate.getX()][aliveCellCoordinate.getY()] = new Cell(CellState.A)
        );

        return startMutations(initialConfig.getNumberOfGenerations(), universe);
    }

    @Override
    public List<CellCoordinates> startMutations(int numberOfGenerations, Universe universe) {
        List<CellCoordinates> cellsToChangeState = new ArrayList<>();
        List<CellCoordinates> liveCells = new ArrayList<>();

        Cell[][] grid = universe.getGrid();

        for (int k = 0; k < numberOfGenerations; k++) {
            cellsToChangeState.clear();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int aliveNeighbours = universe.getAliveNeighbours(i, j);
                    boolean isChangingPossible = GameRules.shouldChangeState(grid[i][j], aliveNeighbours);
                    if (isChangingPossible) {
                        cellsToChangeState.add(new CellCoordinates(i, j));

                    }
                }
            }

            universe.changeState(cellsToChangeState);
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getState().equals(CellState.A)) {
                    liveCells.add(new CellCoordinates(i,j));
                }
            }
        }
        return liveCells;
    }
}
