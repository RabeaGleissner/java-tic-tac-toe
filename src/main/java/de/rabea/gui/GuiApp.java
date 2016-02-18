package de.rabea.gui;

import de.rabea.game.Board;

public class GuiApp {

    private ViewUpdater viewUpdater;

    public GuiApp(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void start() {
        Board board = new Board(3);
        GuiPlayer guiPlayer = new GuiPlayer();
        viewUpdater.showBoard(guiPlayer, board);
        GuiGame game = createGame(board, guiPlayer);
        game.playGame();
    }

    public GuiGame createGame(Board board, GuiPlayer guiPlayer) {
        return new GuiGame(board, guiPlayer);
    }
}
