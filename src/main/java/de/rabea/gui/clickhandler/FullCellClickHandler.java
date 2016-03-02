package de.rabea.gui.clickhandler;

import de.rabea.game.Board;
import de.rabea.game.GameRunner;
import de.rabea.gui.ClickHandler;
import de.rabea.gui.ViewUpdater;
import de.rabea.player.GuiPlayer;

public class FullCellClickHandler implements ClickHandler {


    private ViewUpdater viewUpdater;
    private GuiPlayer guiPlayer;
    private Board board;
    private GameRunner gameRunner;

    public FullCellClickHandler(ViewUpdater viewUpdater, GuiPlayer guiPlayer, Board board, GameRunner gameRunner) {
        this.viewUpdater = viewUpdater;
        this.guiPlayer = guiPlayer;
        this.board = board;
        this.gameRunner = gameRunner;
    }

    @Override
    public void action(String position) {
        viewUpdater.showBoard(guiPlayer, board, gameRunner, true);
    }
}
