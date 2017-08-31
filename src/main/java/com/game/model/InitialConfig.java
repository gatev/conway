package com.game.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class InitialConfig {
    @Min(1)
    @Max(Integer.MAX_VALUE)
    int verticalSize;
    @Min(1)
    @Max(Integer.MAX_VALUE)
    int horizontalSize;

    @NotNull
    @Min(1)
    int numberOfGenerations;

    @NotNull
    List<CellCoordinates> listAliveCells;

    public InitialConfig(int verticalSize, int horizontalSize, int numberOfGenerations, List<CellCoordinates> listAliveCells) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        this.numberOfGenerations = numberOfGenerations;
        this.listAliveCells = listAliveCells;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public List<CellCoordinates> getListAliveCells() {
        return listAliveCells;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }
}
