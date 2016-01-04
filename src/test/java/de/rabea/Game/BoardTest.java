package de.rabea.game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.rabea.game.Cell.*;
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