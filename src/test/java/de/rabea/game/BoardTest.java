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

    @Before
    public void setup() {
    }

    @Test
    public void creates3x3Board() {
        Board board = new Board(3);
        assertEquals(9, board.cells().length);
    }

    @Test
    public void creates4x4Board() {
        Board board = new Board(4);
        assertEquals(16, board.cells().length);
    }

    @Test
    public void placesAMark() {
        Board board = new Board(3);
        Mark[] cells = {EMPTY, X, EMPTY,
                        EMPTY, EMPTY, EMPTY,
                        EMPTY, EMPTY, EMPTY};
        board.placeMark(1, X);
        assertArrayEquals(cells, board.cells());
    }

    @Test
    public void boardIsEmpty() {
        Board emptyBoard = new Board(3);
        assertFalse(emptyBoard.isFull());
    }

    @Test
    public void boardIsFull() {
        Board fullBoard = full3x3BoardNoWinner();
        assertTrue(fullBoard.isFull());
    }

    @Test
    public void isChosenPositionStillFree() {
        Board emptyBoard = new Board(3);
        assertEquals(true, emptyBoard.isPositionAvailable(1));
    }

    @Test
    public void markedPositionIsNoLongerAvailableOn3x3Board() {
        Board board = new Board(3);
        board.placeMark(1, X);
        assertEquals(false, board.isPositionAvailable(1));
    }

    @Test
    public void markedPositionIsNoLongerAvailableOn4x4Board() {
        Board board = new Board(4);
        board.placeMark(1, X);
        assertEquals(false, board.isPositionAvailable(1));
    }

    @Test
    public void knowsWhenPositionIsUnavailableOn3x3Board() {
        Board board = new Board(3);
        int positionNotOnBoard = 9;
        assertFalse(board.isPositionAvailable(positionNotOnBoard));
    }

    @Test
    public void knowsWhenPositionIsAvailableOn4x4Board() {
        Board board = new Board(4);
        int positionOnBoard = 9;
        assertTrue(board.isPositionAvailable(positionOnBoard));
    }

    @Test
    public void listsAllEmptyPositionsFor3x3Board() {
        Board board = new Board(3);
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(6, O);
        List<Integer> emptyCells = Arrays.asList(0, 4, 5, 7, 8);
        assertEquals(emptyCells, board.emptyCells());
    }

    @Test
    public void listsAllEmptyPositionsFor4x4Board() {
        Board board = new Board(4);
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(6, O);
        List<Integer> emptyCells = Arrays.asList(0, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertEquals(emptyCells, board.emptyCells());
    }

    @Test
    public void indexOfLastCellInEachRow() {
        Board board = new Board(3);
        List<Integer> cells = new ArrayList<>(Arrays.asList(2, 5, 8));
        assertEquals(cells, board.indexOfLastCellPerRow());
    }

    @Test
    public void indexOfLastCellOfBoard() {
        Board board = new Board(3);
        assertEquals(8, board.indexOfLastCell());
    }

    @Test
    public void widthAndHeightOfTheBoard() {
        Board board = new Board(3);
        assertEquals(3, board.getSize());
    }

    @Test
    public void isEndOfLastRowFor3x3Board() {
        Board board = new Board(3);
        assertTrue(board.isIndexOfLastCell(8));
        assertFalse(board.isIndexOfLastCell(7));
    }

    @Test
    public void isEndOfLastRowFor4x4Board() {
        Board board = new Board(4);
        assertTrue(board.isIndexOfLastCell(15));
        assertFalse(board.isIndexOfLastCell(7));
    }

    @Test
    public void isEndOfFirstOrSecondRowOn3x3Board() {
        Board board = new Board(3);
        assertTrue(board.isIndexOfEndOfFirstOrSecondRow(2));
        assertTrue(board.isIndexOfEndOfFirstOrSecondRow(5));
        assertFalse(board.isIndexOfEndOfFirstOrSecondRow(1));
        assertFalse(board.isIndexOfEndOfFirstOrSecondRow(8));
    }

    @Test
    public void isEndOfFirstOrSecondRowOn4x4Board() {
        Board board = new Board(4);
        assertTrue(board.isIndexOfEndOfFirstOrSecondRow(3));
        assertTrue(board.isIndexOfEndOfFirstOrSecondRow(7));
        assertFalse(board.isIndexOfEndOfFirstOrSecondRow(15));
    }

    @Test
    public void allRowsOfA4x4Game() {
        Board board = new Board(4);
        board.placeMark(1, X);
        board.placeMark(8, O);
        board.placeMark(9, X);
        board.placeMark(11, O);
        List<Line> rows = board.getRows();
        assertEquals(4, rows.size());
        assertEquals(4, rows.get(0).allMarks().length);
        assertArrayEquals(rows.get(0).allMarks(), new Mark[]{EMPTY, X, EMPTY, EMPTY});
        assertArrayEquals(rows.get(1).allMarks(), new Mark[]{EMPTY, EMPTY, EMPTY, EMPTY});
        assertArrayEquals(rows.get(2).allMarks(), new Mark[]{O, X, EMPTY, O});
        assertArrayEquals(rows.get(3).allMarks(), new Mark[]{EMPTY, EMPTY, EMPTY, EMPTY});
    }

    @Test
    public void allVerticalsOfA4x4Game() {
        Board board = verticalsWinning4x4Board();
        List<Line> verticals = board.getColumns();
        assertEquals(4, verticals.size());
        assertEquals(4, verticals.get(0).allMarks().length);
        assertArrayEquals(verticals.get(0).allMarks(), new Mark[]{X, X, X, X});
        assertArrayEquals(verticals.get(1).allMarks(), new Mark[]{X, O, O, EMPTY});
        assertArrayEquals(verticals.get(2).allMarks(), new Mark[]{O, O, X, EMPTY});
        assertArrayEquals(verticals.get(3).allMarks(), new Mark[]{EMPTY, EMPTY, O, EMPTY});
    }

    @Test
    public void allDiagonalsOfA4x4Game() {
        Board board = backwardsDiagonalsWinning4x4Board();
        List<Line> diagonals = board.getDiagonals();
        assertEquals(2, diagonals.size());
        assertArrayEquals(diagonals.get(0).allMarks(), new Mark[]{X, X, EMPTY, EMPTY});
        assertArrayEquals(diagonals.get(1).allMarks(), new Mark[]{X, X, X, X});
    }

    @Test
    public void gameOverWithoutWinnerOn3x3() {
        Board board = full3x3BoardNoWinner();
        assertTrue(board.gameOver());
        assertFalse(board.hasWinner());
    }

    @Test
    public void gameOverWithoutWinnerOn4x4() {
        Board board = full4x4BoardNoWinner();
        assertTrue(board.gameOver());
        assertFalse(board.hasWinner());
    }

    @Test
    public void gameOverWithWinnerOn3x3() {
        Board board = horizontalsWinning3x3Board();
        assertTrue(board.gameOver());
    }

    @Test
    public void gameOverWithWinnerOn4x4() {
        Board board = horizontalsWinning4x4Board();
        assertTrue(board.gameOver());
    }

    @Test
    public void verticalWinningCombinationOn3x3() {
        Board board = verticalsWinning3x3Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void verticalWinningCombinationOn4x4() {
        Board board = verticalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void horizontalWinningCombinationOn3x3() {
        Board board = horizontalsWinning3x3Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void horizontalWinningCombinationOn4x4() {
        Board board = horizontalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void diagonalWinningCombinationOn3x3() {
        Board board = diagonalsWinning3x3Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void diagonalWinningCombinationOn4x4() {
        Board board = diagonalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void playerXIsWinnerOn3x3() {
        Board boardWithWinnerX = verticalsWinning3x3Board();
        assertEquals(X, boardWithWinnerX.winningPlayerMark());
    }

    @Test
    public void gameIsDrawn() {
        Board drawnGameBoard = full3x3BoardNoWinner();
        assertTrue(drawnGameBoard.isDrawn());
    }

    @Test
    public void nullWhenNoWinner() {
        Board fullBoardNoWinner = full3x3BoardNoWinner();
        assertEquals(null, fullBoardNoWinner.winningPlayerMark());
    }

    @Test
    public void placesMarkOnNewBoard() {
        Board board = new Board(3);
        board.placeMarkOnNewBoard(1, X, board);
        assertEquals(9, board.emptyCells().size());
    }
    @Test
    public void knowsThatThereIsAWinner() {
        Board board = backwardsDiagonalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    private Board full3x3BoardNoWinner() {
        return new Board(
                X, X, O,
                O, O, X,
                X, O, X);
    }
    private Board full4x4BoardNoWinner() {
        return new Board(
                X, X, O, O,
                O, O, X, X,
                X, O, X, O,
                X, O, X, O);
    }
    private Board horizontalsWinning3x3Board() {
        return new Board(
                X, X, X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, O, O);
    }
    private Board horizontalsWinning4x4Board() {
        return new Board(
                X, X, X, X,
                EMPTY, EMPTY, EMPTY,O,
                EMPTY, O, O, O,
                X, O, X, O);
    }
    private Board verticalsWinning3x3Board() {
        return new Board(
                X, X, O,
                X, O, O,
                X, O, X);
    }
    private Board verticalsWinning4x4Board(){
        return new Board(X, X, O, EMPTY,
                         X, O, O, EMPTY,
                         X, O, X, O,
                         X, EMPTY, EMPTY, EMPTY);
    }
    private Board diagonalsWinning3x3Board() {
        return new Board(
                X, X, O,
                EMPTY, X, EMPTY,
                EMPTY, O, X);
    }
    private Board diagonalsWinning4x4Board() {
        return new Board(
                X,     X,     O,     O,
                EMPTY, X,     EMPTY, O,
                EMPTY, O,     X,     O,
                EMPTY, EMPTY, EMPTY, X);
    }
    private Board backwardsDiagonalsWinning4x4Board() {
        return new Board(
                X, O, O, X,
                O, X, X, O,
                O, X, EMPTY, EMPTY,
                X, EMPTY, EMPTY, EMPTY);
    }

}