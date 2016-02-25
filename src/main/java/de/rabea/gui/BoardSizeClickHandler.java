package de.rabea.gui;

public class BoardSizeClickHandler implements ClickHandler {
    private final GuiApp guiApp;

    public BoardSizeClickHandler(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void action(String boardSize) {
        guiApp.prepareGameForPlaying(boardSize);
    }
}
