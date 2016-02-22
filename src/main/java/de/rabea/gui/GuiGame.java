package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

public class GuiGame {

    private NextGuiPlayer otherPlayer;
    private NextGuiPlayer currentPlayer;
    private BoardView view;
    private Board board;
    private ViewUpdater viewUpdater;

    public GuiGame(NextGuiPlayer p1, NextGuiPlayer p2, BoardView view) {
        this.currentPlayer = p1;
        this.otherPlayer = p2;
        this.view = view;

    }

    public GuiGame(Board board, ViewUpdater viewUpdater) {
        this.board = board;
        this.viewUpdater = viewUpdater;
    }

    public void playRound(int position, Mark mark) {
        board.placeMarkOnExistingBoard(position, mark);
        view.draw(board);
        if (board.gameOver()) {
            viewUpdater.showGameOverView();
        }
    }
}
