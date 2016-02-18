package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

public class GuiGame {

    private Board board;

    public GuiGame(Board board) {
        this.board = board;
    }

    public void playRound(int position) {
        board.placeMarkOnExistingBoard(position, Mark.X);
    }
}
