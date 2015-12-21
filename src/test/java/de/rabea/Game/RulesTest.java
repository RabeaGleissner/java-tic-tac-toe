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
        assertFalse(rules.winner());
    }

    @Test
    public void verticalWinningCombination() {
        rules = new Rules(new VerticalWinningBoard());
        assertTrue(rules.winner());

    }

    @Test
    public void horizontalWinningCombination() {
        rules = new Rules(new HorizontalWinningBoard());
        assertTrue(rules.winner());
    }

    @Test
    public void diagonalWinningBoard() {
        rules = new Rules(new DiagonalWinningBoard());
        assertTrue(rules.winner());
    }

    public class DiagonalWinningBoard extends Board {
        private Cell[] cells;
        public DiagonalWinningBoard() {
            this.cells = new Cell[]{X, X, O,
                                EMPTY, X, EMPTY,
                                EMPTY, O, X};
        }
        @Override
        public Cell[] returnCells() {
            return cells;
        }
    }

    public class HorizontalWinningBoard extends Board {
        private Cell[] cells;
        public HorizontalWinningBoard() {
            this.cells = new Cell[]{X, X, X,
                                EMPTY, EMPTY, EMPTY,
                                    EMPTY, O, O};
        }
        @Override
        public Cell[] returnCells() {
            return cells;
        }
    }

    public class FullBoardNoWinner extends Board {
        private Cell[] cells;

        public FullBoardNoWinner() {
            this.cells = new Cell[]{X, X, O,
                                    O, O, X,
                                    X, O, X};
        }
        @Override
        public Cell[] returnCells() {
            return cells;
        }
        @Override
        public boolean isFull() {
            return true;
        }

    }

    public class VerticalWinningBoard extends Board {
        private Cell[] cells;

        public VerticalWinningBoard() {
            this.cells = new Cell[]{X, X, O,
                                    X, O, O,
                                    X, O, X};
        }

        @Override
        public Cell[] returnCells() {
            return cells;
        }

    }
}