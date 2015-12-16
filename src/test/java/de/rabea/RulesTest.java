package de.rabea;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RulesTest {

    private Board board;
    private Rules rules;

    @Before
    public void setup() {
        board = new Board();
        rules = new Rules(board);
    }
    @Test
    public void gameOverWithoutWinner() {
        assertTrue(rules.gameOver());
        assertFalse(rules.winner());
    }

}