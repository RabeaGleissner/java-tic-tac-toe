package de.rabea.game;

import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void hasAWinnerWhenAllFourCellsAreTheSameAndNotEmpty() {
        Line line = new Line(X, X, X, X);
        assertTrue(line.hasWinner());
    }

    @Test
    public void threeMixedCellsAreNotAWinner() {
        Line line = new Line(X, X, O);
        assertFalse(line.hasWinner());
    }

    @Test
    public void fourMixedCellsAreNotAWinner() {
        Line line = new Line(X, X, O, O);
        assertFalse(line.hasWinner());
    }
}