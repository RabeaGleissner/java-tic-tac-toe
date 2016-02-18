package de.rabea.gui;

import de.rabea.game.Board;

public class GuiApp {

    private ViewUpdater viewUpdater;

    public GuiApp(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void start() {
        Board board = new Board(3);
        GuiGame game = createGame(board);
        GuiPlayer guiPlayer = new GuiPlayer(game);
        viewUpdater.showBoard(guiPlayer, board);
    }

    public GuiGame createGame(Board board) {
        return new GuiGame(board, viewUpdater);
    }
}
