package de.rabea.gui;

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
            guiApp.createGame(GuiHumanVsGuiHuman);
        } else {
            guiApp.createGame(GuiHumanVsComputer);
        }

    }
}
