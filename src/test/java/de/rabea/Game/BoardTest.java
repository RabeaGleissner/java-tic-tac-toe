package de.rabea.game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.rabea.game.Cell.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;


public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void initiatesAnEmptyBoard() {
        Cell[] emptyBoard = {EMPTY, EMPTY, EMPTY,
                             EMPTY, EMPTY, EMPTY,
                             EMPTY, EMPTY, EMPTY};
        assertArrayEquals(emptyBoard, board.cells());
    }

    @Test
    public void placesAMark() {
        Cell[] cells = {EMPTY, X, EMPTY,
                        EMPTY, EMPTY, EMPTY,
                        EMPTY, EMPTY, EMPTY};
        assertArrayEquals(cells, board.placeMark(1, X));
    }

    @Test
    public void boardIsEmpty() {
        assertFalse(board.isFull());
    }

    @Test
    public void boardIsFull() {
        fillUpBoard();
        assertTrue(board.isFull());
    }

    @Test
    public void isChosenPositionStillFree() {
        assertEquals(true, board.isPositionAvailable(1));
    }

    @Test
    public void isNotAValidUserChoice() {
        board.placeMark(1, X);
        assertEquals(false, board.isPositionAvailable(1));
    }

    @Test
    public void switchesMarkXtoO() {
        assertEquals(O, board.switchMark(X));
    }

    @Test
    public void switchesMarkOToX() {
        assertEquals(X, board.switchMark(O));
    }

    @Test
    public void checkIf9IsAnAvailablePosition() {
        assertFalse(board.isPositionAvailable(9));
    }

    @Test
    public void returnsAnArrayOfAllEmptyPosition() {
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(6, O);
        List<Integer> emptyCells = new ArrayList<Integer>(Arrays.asList(0,4,5,7,8));
        assertEquals(emptyCells, board.emptyCells());
    }

    @Test
    public void indexOfLastCellInEachRow() {
        List<Integer> cells = new ArrayList<Integer>(Arrays.asList(2,5,8));
        assertEquals(cells, board.indexOfLastCellPerRow());
    }

    @Test
    public void indexOfLastCellOfBoard() {
        assertEquals(8, board.indexOfLastCell());
    }

    @Test
    public void itKnowsTheWidthOfTheBoard() {
        assertEquals(3, board.getDimension());
    }

    @Test
    public void itGetsAllRowsOfTheBoard() {
        List<List<Integer>> rows = new ArrayList<List<Integer>>();
        List<Integer> row1 = new ArrayList<Integer>(Arrays.asList(0,1,2));
        List<Integer> row2 = new ArrayList<Integer>(Arrays.asList(3,4,5));
        List<Integer> row3 = new ArrayList<Integer>(Arrays.asList(6,7,8));
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        assertEquals(rows, board.getRows());
    }

    @Test
    public void isEndOfLastRow() {
        assertTrue(board.isLastCell(8));
        assertFalse(board.isLastCell(7));
    }

    @Test
    public void isEndOfFirstOrSecondRow() {
        assertTrue(board.isEndOfFirstOrSecondRow(2));
        assertTrue(board.isEndOfFirstOrSecondRow(5));
        assertFalse(board.isEndOfFirstOrSecondRow(1));
        assertFalse(board.isEndOfFirstOrSecondRow(8));
    }

    @Test
    public void gameOverWithoutWinner() {
        board = new FullBoardNoWinner();
        assertTrue(board.gameOver());
        assertFalse(board.hasWinner());
    }

    @Test
    public void gameOverWithWinner() {
        board = new HorizontalWinningBoard();
        assertTrue(board.gameOver());
    }

    @Test
    public void verticalWinningCombination() {
        board = new VerticalWinningBoard();
        assertTrue(board.hasWinner());
    }

    @Test
    public void horizontalWinningCombination() {
        board = new HorizontalWinningBoard();
        assertTrue(board.hasWinner());
    }

    @Test
    public void diagonalWinningBoard() {
        board = new DiagonalWinningBoard();
        assertTrue(board.hasWinner());
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

    private void fillUpBoard() {
        board.placeMark(0,X);
        board.placeMark(1,X);
        board.placeMark(2,O);
        board.placeMark(3,X);
        board.placeMark(4,X);
        board.placeMark(5,O);
        board.placeMark(6,X);
        board.placeMark(7,O);
        board.placeMark(8,O);
    }
}