package de.rabea.gui.clickhandler;

import de.rabea.game.GameRunner;
import de.rabea.gui.ClickHandler;

import static de.rabea.game.GameMode.GuiHumanVsComputer;
import static de.rabea.game.GameMode.GuiHumanVsGuiHuman;

public class GameModeClickHandler implements ClickHandler {

    private final GameRunner gameRunner;

    public GameModeClickHandler(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    @Override
    public void action(String gameOption) {
        if (gameOption.equals("HumanvsHuman")) {
            gameRunner.setGameAndDisplayBoardSizeOptions(GuiHumanVsGuiHuman);
        } else {
            gameRunner.setGameAndDisplayBoardSizeOptions(GuiHumanVsComputer);
        }

    }
}
