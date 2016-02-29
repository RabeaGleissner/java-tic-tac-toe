package de.rabea.gui.clickhandler;

import de.rabea.game.Board;
import de.rabea.gui.ClickHandler;
import de.rabea.gui.GuiApp;
import de.rabea.gui.GuiPlayer;
import de.rabea.gui.ViewUpdater;

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
