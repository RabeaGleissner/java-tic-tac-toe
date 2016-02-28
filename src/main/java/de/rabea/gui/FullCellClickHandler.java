package de.rabea.gui;

import de.rabea.game.Board;

public class FullCellClickHandler implements ClickHandler {


    private ViewUpdater viewUpdater;
    private GuiPlayer guiPlayer;
    private Board board;
    private GuiApp guiApp;

    public FullCellClickHandler(ViewUpdater viewUpdater, GuiPlayer guiPlayer, Board board, GuiApp guiApp) {
        this.viewUpdater = viewUpdater;
        this.guiPlayer = guiPlayer;
        this.board = board;
        this.guiApp = guiApp;
    }

    @Override
    public void action(String position) {
        viewUpdater.showBoard(guiPlayer, board, guiApp, true);
    }
}
