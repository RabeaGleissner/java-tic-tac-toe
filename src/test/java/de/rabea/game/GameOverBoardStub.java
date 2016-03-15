package de.rabea.game;

public class GameOverBoardStub extends Board {
    @Override
    public boolean gameOver() {
        return true;
    }

    @Override
    public boolean hasWinner() {
        return true;
    }
}
