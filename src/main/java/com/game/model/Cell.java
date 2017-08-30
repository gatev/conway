package com.game.model;

public enum Cell {
    A(State.A), D(State.D);

    Cell(State state) {
        this.state = state;
    }

    private State state;

    public State getState() {
        return state;
    }

    public enum State {
        A, D
    }

}
