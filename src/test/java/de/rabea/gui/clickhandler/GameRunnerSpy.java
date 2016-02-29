package de.rabea.gui.clickhandler;

import de.rabea.game.GameMode;
import de.rabea.game.GameRunner;

public class GameRunnerSpy extends GameRunner {
    public boolean createBoardAndPlayWasCalled = false;
    public int givenBoardSize;
    public boolean displayGameModeOptionsWasCalled;
    public GameMode createsGameWithThisMode = null;

    public GameRunnerSpy() {
        super(null, null);
    }

    @Override
    public void playWithFreshBoard(int boardSize) {
        createBoardAndPlayWasCalled = true;
        givenBoardSize = boardSize;
    }

    @Override
    public void displayGameModeOptions() {
        displayGameModeOptionsWasCalled = true;
    }

    @Override
    public void setGameAndDisplayBoardSizeOptions(GameMode gameMode) {
        createsGameWithThisMode = gameMode;
    }
}
