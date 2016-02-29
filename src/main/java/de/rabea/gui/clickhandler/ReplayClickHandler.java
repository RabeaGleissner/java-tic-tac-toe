package de.rabea.gui.clickhandler;

import de.rabea.game.GameRunner;
import de.rabea.gui.ClickHandler;

public class ReplayClickHandler implements ClickHandler {

    private final GameRunner gameRunner;

    public ReplayClickHandler(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    @Override
    public void action(String position) {
        gameRunner.displayGameModeOptions();
    }
}
