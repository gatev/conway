package com.game.services;

import com.game.model.*;
import com.game.utils.GameRules;

import java.util.ArrayList;
import java.util.List;

public class GameManagerServiceImpl implements GameManagerService {

    private List<CellCoordinates> liveCells = new ArrayList<>();

    @Override
    public void initGame(InitialConfig initialConfig) {
        final Universe universe = new Universe(initialConfig.getHorizontalSize(), initialConfig.getVerticalSize());
        initialConfig.getListAliveCells().forEach((aliveCellCoordinate) ->
                universe.getGrid()[aliveCellCoordinate.getX()][aliveCellCoordinate.getY()] = new Cell(CellState.A)
        );

        for (int i = 0; i < universe.getHorizontalSize(); i++) {
            for (int j = 0; j < universe.getVerticalSize(); j++) {
                System.out.print(universe.getGrid()[i][j] + " ");
            }
            System.out.println();
        }


        startMutations(initialConfig.getNumberOfGenerations(), universe);

    }

    @Override
    public void startMutations(int numberOfGenerations, Universe universe) {
        List<CellCoordinates> cellsToChangeState = new ArrayList<>();

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
            System.out.println();

        }


        System.out.println("++++++++++++++++++++++++++++++++++");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getState().equals(CellState.A)) {
                    liveCells.add(new CellCoordinates(i,j));
                }
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public List<CellCoordinates> getLiveCells() {
        return this.liveCells;
    }
}
