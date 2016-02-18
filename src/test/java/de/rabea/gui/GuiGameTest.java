package de.rabea.gui;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GuiGameTest {
    GuiPlayer guiPlayer;
    Board board;
    GuiGame guiGame;

    @Before
    public void setup() {
        board = new Board(3);
        guiGame = new GuiGame(board);
        guiPlayer = new GuiPlayer(guiGame);
    }

    @Test
    public void playsOneRound() {
        GuiGame guiGame = new GuiGame(board);
        guiGame.playRound(1);
        assertFalse(board.isPositionAvailable(1));
    }
}