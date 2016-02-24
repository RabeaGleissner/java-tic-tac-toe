package de.rabea.gui;

public class BoardClickHandler implements ClickHandler {
    private final ClickCarrier clickCarrier;
    private final GuiApp guiApp;

    public BoardClickHandler(ClickCarrier clickCarrier, GuiApp guiApp) {
        this.clickCarrier = clickCarrier;
        this.guiApp = guiApp;
    }

    @Override
    public void action(String position) {
        clickCarrier.addMove(convertToInteger(position));
        guiApp.startGame();
    }

    private int convertToInteger(String position) {
        return Integer.parseInt(position);
    }
}
