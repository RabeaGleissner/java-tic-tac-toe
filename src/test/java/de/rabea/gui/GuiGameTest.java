package de.rabea.gui;

import de.rabea.game.Board;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiGameTest {

    @Test
    public void playsOneRound() {
        Board board = new Board(3);
        GuiGame guiGame = new GuiGame(board);
        guiGame.playRound(1);
        assertFalse(board.isPositionAvailable(1));
    }

}