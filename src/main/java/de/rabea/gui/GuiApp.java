package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

public class GuiApp {

    private ViewUpdater viewUpdater;

    public GuiApp(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void start() {
        Board board = new Board(3);
        GuiGame game = createGame(board);
        ClickCarrier carrier = new ClickCarrier();
        NextGuiPlayer guiPlayer = new NextGuiPlayer(Mark.X, carrier);
        viewUpdater.showBoard(carrier, board);
    }

    public GuiGame createGame(Board board) {
        return new GuiGame(board, viewUpdater);
    }
}
