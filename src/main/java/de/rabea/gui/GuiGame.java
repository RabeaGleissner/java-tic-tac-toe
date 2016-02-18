package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

public class GuiGame {

    private Board board;
    private ViewUpdater viewUpdater;

    public GuiGame(Board board, ViewUpdater viewUpdater) {
        this.board = board;
        this.viewUpdater = viewUpdater;
    }

    public void playRound(int position) {
        board.placeMarkOnExistingBoard(position, Mark.X);
        if (board.gameOver()) {
            viewUpdater.showGameOverView();
        }
    }
}
