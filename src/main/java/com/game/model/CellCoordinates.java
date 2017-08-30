package com.game.model;

import javax.validation.constraints.NotNull;

public class CellCoordinates {
    @NotNull
    private int x;
    @NotNull
    private int y;

    public CellCoordinates() {
    }

    public CellCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

