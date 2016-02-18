package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

public class GuiGame {

    private Board board;
    private GuiPlayer guiPlayer;
    private int currentPosition = -1;

    public GuiGame(Board board, GuiPlayer guiPlayer) {
        this.board = board;
        this.guiPlayer = guiPlayer;
    }

    public void playGame() {
        while(!board.gameOver() && guiPlayer.hasNewMove()) {
                currentPosition = guiPlayer.clickedPosition();
                playRound(currentPosition);
        }
    }

    public void playRound(int position) {
        board.placeMarkOnExistingBoard(position, Mark.X);
    }
}
