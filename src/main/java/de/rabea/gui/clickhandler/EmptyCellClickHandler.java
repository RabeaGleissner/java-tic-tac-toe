package de.rabea.gui.clickhandler;

import de.rabea.game.Board;
import de.rabea.gui.ClickHandler;
import de.rabea.gui.GuiApp;
import de.rabea.gui.GuiPlayer;

public class EmptyCellClickHandler implements ClickHandler {
    private final GuiPlayer guiPlayer;
    private final GuiApp guiApp;
    private final Board board;

    public EmptyCellClickHandler(GuiPlayer guiPlayer, GuiApp guiApp, Board board) {
        this.guiPlayer = guiPlayer;
        this.guiApp = guiApp;
        this.board = board;
    }

    @Override
    public void action(String position) {
        guiPlayer.addMove(convertToInteger(position));
        guiApp.playOneRound(board);
    }

    private int convertToInteger(String position) {
        return Integer.parseInt(position);
    }
}
