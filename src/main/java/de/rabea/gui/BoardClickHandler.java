package de.rabea.gui;

public class BoardClickHandler implements ClickHandler {
    private GuiPlayer guiPlayer;

    public BoardClickHandler(GuiPlayer guiPlayer) {
        this.guiPlayer = guiPlayer;
    }

    @Override
    public void action(String position) {
       guiPlayer.click(convertToInteger(position));
    }

    private int convertToInteger(String position) {
        return Integer.parseInt(position);
    }
}
