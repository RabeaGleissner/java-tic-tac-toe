package de.rabea.gui.clickhandler;

import de.rabea.game.GameRunner;
import de.rabea.gui.ClickHandler;

public class BoardSizeClickHandler implements ClickHandler {
    private final GameRunner gameRunner;

    public BoardSizeClickHandler(GameRunner guiGameRunner) {
        this.gameRunner = guiGameRunner;
    }

    @Override
    public void action(String boardSize) {
        if (boardSize.equals("3x3")) {
            gameRunner.playWithFreshBoard(3);
        } else {
            gameRunner.playWithFreshBoard(4);
        }
    }
}
