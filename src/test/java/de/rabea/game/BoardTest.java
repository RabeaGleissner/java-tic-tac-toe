package de.rabea.game;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.rabea.game.Mark.*;
import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class BoardTest {

    @Test
    public void boardIsFull() {
        Board fullBoard = full3x3BoardNoWinner();
        assertTrue(fullBoard.isFull());
    }

    @Test
    public void positionIsAvailable() {
        Board emptyBoard = new Board(3);
        assertEquals(true, emptyBoard.isPositionAvailable(1));
    }

    @Test
    public void positionIsNoLongerAvailable() {
        Board nextBoard = new Board(3).placeMark(1, X);
        assertFalse(nextBoard.isPositionAvailable(1));
    }

    @Test
    public void listsAllEmptyPositionsFor3x3Board() {
        Board board = new Board(EMPTY, X, O,
                                X, EMPTY, EMPTY,
                                O, EMPTY, EMPTY);
        assertEquals(asList(0, 4, 5, 7, 8), board.emptyCells());
    }

    @Test
    public void listsAllEmptyPositionsFor4x4Board() {
        Board board = new Board(EMPTY, X, O, X,
                                EMPTY, EMPTY, O, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY);
        List<Integer> emptyCells = asList(0, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertEquals(emptyCells, board.emptyCells());
    }

    @Test
    public void indexOfLastCellInEachRow() {
        Board board = new Board(3);
        assertEquals(new ArrayList<>(asList(2, 5, 8)), board.indexOfLastCellPerRow());
    }

    @Test
    public void indexOfLastCellOfBoard() {
        Board board = new Board(3);
        assertEquals(8, board.indexOfLastCell());
    }

    @Test
    public void widthAndHeightOf3x3Board() {
        Board board = new Board(3);
        assertEquals(3, board.getDimension());
    }

    @Test
    public void knowsIfIndexIsLastCellOfBoard() {
        Board board = new Board(3);
        assertTrue(board.isIndexOfLastCell(8));
        assertFalse(board.isIndexOfLastCell(7));
    }

    @Test
    public void boardHasWinnerWithHorizontalLine() {
        assertTrue(horizontalsWinning3x3Board().gameOver());
    }

    @Test
    public void boardHasWinnerWithVerticalLine() {
        assertTrue(verticalsWinning3x3Board().hasWinner());
    }

    @Test
    public void boardHasWinnerWithDiagonalLine() {
        assertTrue(diagonalsWinning3x3Board().hasWinner());
    }

    @Test
    public void playerXIsWinner() {
        assertEquals(X, verticalsWinning3x3Board().winningPlayerMark());
    }

    @Test
    public void gameIsDrawn() {
        assertTrue(full3x3BoardNoWinner().isDrawn());
    }

    @Test
    public void nullWhenNoWinner() {
        assertEquals(null, full3x3BoardNoWinner().winningPlayerMark());
    }

    @Test
    public void placesMarkOnNewBoard() {
        Board board = new Board(3);
        board.placeMark(1, X);
        assertEquals(9, board.emptyCells().size());
    }

    @Test
    public void mapsPositionsOverToMarks() {
        Board board = new Board(3);

        Map<Integer, Mark> marks = board.cellsWithIndex();
        for (Map.Entry<Integer, Mark> pair : marks.entrySet()) {
            assertTrue(pair.getValue() == EMPTY);
        }
    }

    @Test
    public void returnsTrueIfCellIsAtTheEndOfARow() {
        Board board = new Board(3);
        assertTrue(board.isEndOfRow(2));
    }

    @Test
    public void returnsFalseIfCellIsAtTheEndOfARow() {
        Board board = new Board(3);
        assertFalse(board.isEndOfRow(1));
    }

    @Test
    public void returnsTrueIfItIsNotTheLastCell() {
        Board board = new Board(3);
        assertTrue(board.isNotLastCellIndex(1));
    }

    private Board full3x3BoardNoWinner() {
        return new Board(
                X, X, O,
                O, O, X,
                X, O, X);
    }

    private Board horizontalsWinning3x3Board() {
        return new Board(
                X, X, X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, O, O);
    }

    private Board verticalsWinning3x3Board() {
        return new Board(
                X, X, O,
                X, O, O,
                X, O, X);
    }

    private Board diagonalsWinning3x3Board() {
        return new Board(
                X, X, O,
                EMPTY, X, EMPTY,
                EMPTY, O, X);
    }
}