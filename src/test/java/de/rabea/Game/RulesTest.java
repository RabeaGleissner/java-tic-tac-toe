package de.rabea.game;

import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Cell.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        rules = new Rules(new FullBoardNoWinner());
        assertTrue(rules.gameOver());
        assertFalse(rules.hasWinner());
    }

    @Test
    public void gameOverWithWinner() {
        rules = new Rules(new HorizontalWinningBoard());
        assertTrue(rules.gameOver());
    }

    @Test
    public void verticalWinningCombination() {
        rules = new Rules(new VerticalWinningBoard());
        assertTrue(rules.hasWinner());
    }

    @Test
    public void horizontalWinningCombination() {
        rules = new Rules(new HorizontalWinningBoard());
        assertTrue(rules.hasWinner());
    }

    @Test
    public void diagonalWinningBoard() {
        rules = new Rules(new DiagonalWinningBoard());
        assertTrue(rules.hasWinner());
    }

    public class DiagonalWinningBoard extends Board {
        public DiagonalWinningBoard() {
            super(new Cell[]{X, X, O,
                                EMPTY, X, EMPTY,
                                EMPTY, O, X});
        }
    }

    public class HorizontalWinningBoard extends Board {
        public HorizontalWinningBoard() {
            super(new Cell[]{X, X, X,
                            EMPTY, EMPTY, EMPTY,
                            EMPTY, O, O});
        }
    }

    public class FullBoardNoWinner extends Board {

        public FullBoardNoWinner() {
            super(new Cell[]{X, X, O,
                             O, O, X,
                             X, O, X});
        }
    }

    public class VerticalWinningBoard extends Board {

        public VerticalWinningBoard() {
            super(new Cell[]{X, X, O,
                             X, O, O,
                             X, O, X});
        }
    }
}