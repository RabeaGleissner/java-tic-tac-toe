package de.rabea.gui.clickhandler;

import de.rabea.gui.ClickHandler;
import de.rabea.gui.GuiApp;

public class BoardSizeClickHandler implements ClickHandler {
    private final GuiApp guiApp;

    public BoardSizeClickHandler(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void action(String boardSize) {
        guiApp.startGame(boardSize);
    }
}
