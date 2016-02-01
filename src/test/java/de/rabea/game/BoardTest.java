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
    public void placesAMark() {
        Board board = new Board();
        Mark[] cells = {EMPTY, X, EMPTY,
                        EMPTY, EMPTY, EMPTY,
                        EMPTY, EMPTY, EMPTY};
        board.placeMark(1, X);
        assertArrayEquals(cells, board.cells());
    }

    @Test
    public void boardIsEmpty() {
        Board emptyBoard = new Board();
        assertFalse(emptyBoard.isFull());
    }

    @Test
    public void boardIsFull() {
        Board fullBoard = fullBoardNoWinner();
        assertTrue(fullBoard.isFull());
    }

    @Test
    public void isChosenPositionStillFree() {
        Board emptyBoard = new Board();
        assertEquals(true, emptyBoard.isPositionAvailable(1));
    }

    @Test
    public void markedPositionIsNoLongerAvailable() {
        Board board = new Board();
        board.placeMark(1, X);
        assertEquals(false, board.isPositionAvailable(1));
    }

    @Test
    public void itKnowsWhenAPositionIsUnavailable() {
        Board board = new Board();
        int positionNotOnBoard = 9;
        assertFalse(board.isPositionAvailable(positionNotOnBoard));
    }

    @Test
    public void returnsAllEmptyPositions() {
        Board board = new Board();
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(6, O);
        List<Integer> emptyCells = Arrays.asList(0, 4, 5, 7, 8);
        assertEquals(emptyCells, board.emptyCells());
    }

    @Test
    public void indexOfLastCellInEachRow() {
        Board board = new Board();
        List<Integer> cells = new ArrayList<>(Arrays.asList(2, 5, 8));
        assertEquals(cells, board.indexOfLastCellPerRow());
    }

    @Test
    public void indexOfLastCellOfBoard() {
        Board board = new Board();
        assertEquals(8, board.indexOfLastCell());
    }

    @Test
    public void widthAndHeightOfTheBoard() {
        Board board = new Board();
        assertEquals(3, board.getDimension());
    }

    @Test
    public void isEndOfLastRow() {
        Board board = new Board();
        assertTrue(board.isLastCell(8));
        assertFalse(board.isLastCell(7));
    }

    @Test
    public void isEndOfFirstOrSecondRow() {
        Board board = new Board();
        assertTrue(board.isEndOfFirstOrSecondRow(2));
        assertTrue(board.isEndOfFirstOrSecondRow(5));
        assertFalse(board.isEndOfFirstOrSecondRow(1));
        assertFalse(board.isEndOfFirstOrSecondRow(8));
    }

    @Test
    public void gameOverWithoutWinner() {
        Board board = fullBoardNoWinner();
        assertTrue(board.gameOver());
        assertFalse(board.hasWinner());
    }

    @Test
    public void gameOverWithWinner() {
        Board board = horizontalsWinningBoard();
        assertTrue(board.gameOver());
    }

    @Test
    public void verticalWinningCombination() {
        Board board = verticalsWinningBoard();
        assertTrue(board.hasWinner());
    }

    @Test
    public void horizontalWinningCombination() {
        Board board = horizontalsWinningBoard();
        assertTrue(board.hasWinner());
    }

    @Test
    public void diagonalWinningBoard() {
        Board board = diagonalsWinningBoard();
        assertTrue(board.hasWinner());
    }

    @Test
    public void playerXIsWinner() {
        Board boardWithWinnerX = verticalsWinningBoard();
        assertEquals(X, boardWithWinnerX.winningPlayerMark());
    }

    @Test
    public void gameIsDrawn() {
        Board drawnGameBoard = fullBoardNoWinner();
        assertTrue(drawnGameBoard.isDrawn());
    }

    @Test
    public void nullWhenNoWinner() {
        Board fullBoardNoWinner = fullBoardNoWinner();
        assertEquals(null, fullBoardNoWinner.winningPlayerMark());
    }

    @Test
    public void placesMarkOnNewBoard() {
        Board board = new Board();
        board.placeMarkOnNewBoard(1, X, board);
        assertFalse(board.isFull());
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