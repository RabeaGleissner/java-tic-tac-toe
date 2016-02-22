package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NextGuiPlayerTest {

    @Test
    public void hasMoveWhenClickCarrierHasSomething() {
        ClickCarrier carrier = ClickCarrier.withMove(3);
        NextGuiPlayer player = new NextGuiPlayer(Mark.X, carrier);
        assertTrue(player.hasMove());
    }

    @Test
    public void returnsMoveFromCarrier() {
        ClickCarrier carrier = ClickCarrier.withMove(5);
        NextGuiPlayer player = new NextGuiPlayer(Mark.X, carrier);
        assertEquals(5, player.getPosition(new Board(3)));
    }

    @Test
    public void returnsMoveExactlyOnce() {
        ClickCarrier carrier = ClickCarrier.withMove(3);
        NextGuiPlayer player = new NextGuiPlayer(O, carrier);
        assertEquals(3, player.getPosition(new Board(3)));
        assertEquals(-1, player.getPosition(new Board(3)));
    }

    @Test
    public void hasNoMoveWhenThereIsNoData() {
        ClickCarrier carrier = ClickCarrier.withNoData();
        NextGuiPlayer player = new NextGuiPlayer(Mark.X, carrier);
        assertFalse(player.hasMove());
    }
}