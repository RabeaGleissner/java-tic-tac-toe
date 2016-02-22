package de.rabea.gui;

public class BoardSizeClickHandler implements ClickHandler {
    GuiApp guiApp;

    public BoardSizeClickHandler(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void action(String position) {
        guiApp.displayBoard();
    }
}
