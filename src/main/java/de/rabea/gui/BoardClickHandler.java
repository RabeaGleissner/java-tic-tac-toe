package de.rabea.gui;

import de.rabea.game.Board;

public class BoardClickHandler implements ClickHandler {
    private final GuiPlayer guiPlayer;
    private final GuiApp guiApp;
    private final Board board;

    public BoardClickHandler(GuiPlayer guiPlayer, GuiApp guiApp, Board board) {
        this.guiPlayer = guiPlayer;
        this.guiApp = guiApp;
        this.board = board;
    }

    @Override
    public void action(String position) {
        guiPlayer.addMove(convertToInteger(position));
        guiApp.playOneRound(board, guiPlayer);
    }

    private int convertToInteger(String position) {
        return Integer.parseInt(position);
    }
}
