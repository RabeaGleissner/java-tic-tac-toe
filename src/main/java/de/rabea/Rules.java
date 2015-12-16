package de.rabea;

public class Rules {

    private final Board board;

    public Rules(Board board) {
        this.board = board;

    }

    public boolean gameOver() {
        return true;
    }

    public boolean winner() {
        return false;
    }
}
