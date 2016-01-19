package de.rabea.game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.rabea.game.Mark.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;


public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void initiatesAnEmptyBoard() {
        Mark[] emptyBoard = {EMPTY, EMPTY, EMPTY,
                             EMPTY, EMPTY, EMPTY,
                             EMPTY, EMPTY, EMPTY};
        assertArrayEquals(emptyBoard, board.cells());
    }

    @Test
    public void placesAMark() {
        Mark[] cells = {EMPTY, X, EMPTY,
                        EMPTY, EMPTY, EMPTY,
                        EMPTY, EMPTY, EMPTY};
        board.placeMark(1, Mark.X);
        assertArrayEquals(cells, board.cells());
    }

    @Test
    public void boardIsEmpty() {
        assertFalse(board.isFull());
    }

    @Test
    public void boardIsFull() {
        board = fullBoardNoWinner();
        assertTrue(board.isFull());
    }

    @Test
    public void isChosenPositionStillFree() {
        assertEquals(true, board.isPositionAvailable(1));
    }

    @Test
    public void isNotAValidUserChoice() {
        board.placeMark(1, Mark.X);
        assertEquals(false, board.isPositionAvailable(1));
    }

    @Test
    public void switchesMarkXtoO() {
        Mark mark = Mark.O;
        assertEquals(mark, mark.switchMark(Mark.X));
    }

    @Test
    public void switchesMarkOToX() {
        Mark mark = Mark.X;
        assertEquals(mark, mark.switchMark(Mark.O));
    }

    @Test
    public void checkIf9IsAnAvailablePosition() {
        assertFalse(board.isPositionAvailable(9));
    }

    @Test
    public void returnsAnArrayOfAllEmptyPosition() {
        board.placeMark(1, Mark.X);
        board.placeMark(2, Mark.O);
        board.placeMark(3, Mark.X);
        board.placeMark(6, Mark.O);
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
        board = fullBoardNoWinner();
        assertTrue(board.gameOver());
        assertFalse(board.hasWinner());
    }

    @Test
    public void gameOverWithWinner() {
        board = horizontalsWinningBoard();
        assertTrue(board.gameOver());
    }

    @Test
    public void verticalWinningCombination() {
        board = verticalsWinningBoard();
        assertTrue(board.hasWinner());
    }

    @Test
    public void horizontalWinningCombination() {
        board = horizontalsWinningBoard();
        assertTrue(board.hasWinner());
    }

    @Test
    public void diagonalWinningBoard() {
        board = diagonalsWinningBoard();
        assertTrue(board.hasWinner());
    }

    private Board fullBoardNoWinner() {
        return new Board(
                new Mark[]{X, X, O,
                           O, O, X,
                           X, O, X}
        );
    }
    private Board horizontalsWinningBoard() {
        return new Board(
                new Mark[]{X,     X,     X,
                           EMPTY, EMPTY, EMPTY,
                           EMPTY, O,     O}
        );
    }
    private Board verticalsWinningBoard() {
        return new Board(
                new Mark[]{X, X, O,
                           X, O, O,
                           X, O, X}
        );
    }

    private Board diagonalsWinningBoard() {
        return new Board(
                new Mark[]{X,     X, O,
                           EMPTY, X, EMPTY,
                           EMPTY, O, X}
        );
    }
}