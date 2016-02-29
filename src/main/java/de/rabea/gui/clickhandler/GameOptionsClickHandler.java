package de.rabea.gui.clickhandler;

import de.rabea.gui.ClickHandler;
import de.rabea.gui.GuiApp;

import static de.rabea.game.GameMode.GuiHumanVsComputer;
import static de.rabea.game.GameMode.GuiHumanVsGuiHuman;

public class GameOptionsClickHandler implements ClickHandler {

    private final GuiApp guiApp;

    public GameOptionsClickHandler(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void action(String gameOption) {
        if (gameOption.equals("HumanvsHuman")) {
            guiApp.createGameAndGetBoardSize(GuiHumanVsGuiHuman);
        } else {
            guiApp.createGameAndGetBoardSize(GuiHumanVsComputer);
        }

    }
}