package de.rabea.gui.clickhandler;

import de.rabea.game.Board;
import de.rabea.game.GameRunner;
import de.rabea.gui.ClickHandler;
import de.rabea.player.GuiPlayer;

public class EmptyCellClickHandler implements ClickHandler {
    private final GuiPlayer guiPlayer;
    private final GameRunner gameRunner;
    private final Board board;

    public EmptyCellClickHandler(GuiPlayer guiPlayer, GameRunner gameRunner, Board board) {
        this.guiPlayer = guiPlayer;
        this.gameRunner = gameRunner;
        this.board = board;
    }

    @Override
    public void action(String position) {
        guiPlayer.addMove(convertToInteger(position));
        gameRunner.playOneRound(board);
    }

    private int convertToInteger(String position) {
        return Integer.parseInt(position);
    }
}
