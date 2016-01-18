package de.rabea.game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.rabea.game.Cell.*;
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
        assertEquals(Mark.O, board.switchMark(Mark.X));
    }

    @Test
    public void switchesMarkOToX() {
        assertEquals(Mark.X, board.switchMark(Mark.O));
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
    public void itGetsAllColumnsOfTheBoard() {
        List<List<Integer>> columns = new ArrayList<List<Integer>>();
        List<Integer> column1 = new ArrayList<Integer>(Arrays.asList(0,3,6));
        List<Integer> column2 = new ArrayList<Integer>(Arrays.asList(1,4,7));
        List<Integer> column3 = new ArrayList<Integer>(Arrays.asList(2,5,8));
        columns.add(column1);
        columns.add(column2);
        columns.add(column3);
        assertEquals(columns, board.getColumns());
    }

    @Test
    public void itGetsTheDiagonals() {
       List<List<Integer>> diagonals = new ArrayList<List<Integer>>();
        List<Integer> diagonal1 = new ArrayList<Integer>(Arrays.asList(0,4,8));
        List<Integer> diagonal2 = new ArrayList<Integer>(Arrays.asList(2,4,6));
        diagonals.add(diagonal1);
        diagonals.add(diagonal2);
        assertEquals(diagonals, board.getDiagonals());
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

    @Test
    public void itKnowsAllLinesOnTheBoard() {
        List<List<Integer>> allLines = new ArrayList<List<Integer>>();
        List<Integer> line1 = new ArrayList<Integer>(Arrays.asList(0,1,2));
        List<Integer> line2 = new ArrayList<Integer>(Arrays.asList(3,4,5));
        List<Integer> line3 = new ArrayList<Integer>(Arrays.asList(6,7,8));
        List<Integer> line4 = new ArrayList<Integer>(Arrays.asList(0,3,6));
        List<Integer> line5 = new ArrayList<Integer>(Arrays.asList(1,4,7));
        List<Integer> line6 = new ArrayList<Integer>(Arrays.asList(2,5,8));
        List<Integer> line7 = new ArrayList<Integer>(Arrays.asList(0,4,8));
        List<Integer> line8 = new ArrayList<Integer>(Arrays.asList(2,4,6));
        allLines.add(line1);
        allLines.add(line2);
        allLines.add(line3);
        allLines.add(line4);
        allLines.add(line5);
        allLines.add(line6);
        allLines.add(line7);
        allLines.add(line8);
        assertEquals(allLines, board.getAllLines());
    }

    private Board fullBoardNoWinner() {
        return new Board(
                new Cell[]{X, X, O,
                           O, O, X,
                           X, O, X}
        );
    }
    private Board horizontalsWinningBoard() {
        return new Board(
                new Cell[]{X,     X,     X,
                           EMPTY, EMPTY, EMPTY,
                           EMPTY, O,     O}
        );
    }
    private Board verticalsWinningBoard() {
        return new Board(
                new Cell[]{X, X, O,
                           X, O, O,
                           X, O, X}
        );
    }

    private Board diagonalsWinningBoard() {
        return new Board(
                new Cell[]{X,     X, O,
                           EMPTY, X, EMPTY,
                           EMPTY, O, X}
        );
    }
}