package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.player.GuiPlayer;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.*;

public class GuiPlayerTest {

    @Test
    public void hasMoveWhenMoveHasBeenAdded() {
        GuiPlayer player = new GuiPlayer(Mark.X);
        player.addMove(1);

        assertTrue(player.hasMove());
    }

    @Test
    public void returnsMove() {
        GuiPlayer player = new GuiPlayer(Mark.X);
        player.addMove(5);

        assertEquals(5, player.getPosition(new Board(3)));
    }

    @Test
    public void returnsMoveExactlyOnce() {
        GuiPlayer player = new GuiPlayer(O);
        player.addMove(3);

        assertEquals(3, player.getPosition(new Board(3)));
        assertEquals(-1, player.getPosition(new Board(3)));
    }

    @Test
    public void hasNoMoveWhenThereIsNoData() {
        GuiPlayer player = new GuiPlayer(Mark.X);

        assertFalse(player.hasMove());
    }
}