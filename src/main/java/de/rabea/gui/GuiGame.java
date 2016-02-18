package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

public class GuiGame {

    private Board board;

    public GuiGame(Board board) {
        this.board = board;
    }

    public void playRound(int position) {
        if (!board.gameOver()) {
            board.placeMarkOnExistingBoard(position, Mark.X);
        } else {
            System.out.println("game is over");
        }
    }
}
