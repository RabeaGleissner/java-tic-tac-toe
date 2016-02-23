package de.rabea.gui;

public class BoardClickHandler implements ClickHandler {
    private ClickCarrier clickCarrier;
    private GuiApp guiApp;

    public BoardClickHandler(ClickCarrier clickCarrier, GuiApp guiApp) {
        this.clickCarrier = clickCarrier;
        this.guiApp = guiApp;
    }


    @Override
    public void action(String position) {
        clickCarrier.addMove(convertToInteger(position));
        guiApp.displayBoard();
    }

    private int convertToInteger(String position) {
        return Integer.parseInt(position);
    }
}
