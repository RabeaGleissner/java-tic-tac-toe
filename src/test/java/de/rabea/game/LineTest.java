package de.rabea.game;

import org.junit.Test;

import static de.rabea.game.Mark.EMPTY;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void noWinnerWhenAllEmpty() {
        Line line = new Line(EMPTY, EMPTY, EMPTY);

        assertFalse(line.hasWinner());
    }

    @Test
    public void hasAWinnerWhenAllThreeCellsAreTheSameAndNotEmpty() {
        Line line = new Line(X, X, X);
        assertTrue(line.hasWinner());
    }

    @Test
    public void mixedCellsAreNotAWinner() {
        Line line = new Line(X, X, Mark.O);
        assertFalse(line.hasWinner());
    }
}