package de.rabea.game;
import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

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
        assertArrayEquals(emptyBoard, board.returnCells());
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
        board.placeMark(1, Cell.X);
        assertEquals(false, board.isPositionAvailable(1));
    }

    @Test
    public void switchesMarkXtoO() {
        assertEquals(Cell.O, board.switchMark(Cell.X));
    }

    @Test
    public void switchesMarkOToX() {
        assertEquals(Cell.X, board.switchMark(Cell.O));
    }

    @Test
    public void returnsThePositionIfItIsValid() {
        FakeUserInterface fakeUserInterface = new FakeUserInterface();
        assertEquals((Integer) 1, board.ensurePositionIsValid(1, fakeUserInterface, new Game(fakeUserInterface)));
    }

    @Test
    public void asksUserAgainForPositionIfInvalid() {
        board.placeMark(1,X);
        FakeUserInterface fakeUserInterface = new FakeUserInterface();
        fakeUserInterface.provideConsoleInput("3");
        board.ensurePositionIsValid(1, fakeUserInterface, new Game(fakeUserInterface));
        assertTrue(fakeUserInterface.askForPositionWasCalled);
    }

    @Test
    public void checkIf9IsAnAvailablePosition() {
        assertFalse(board.isPositionAvailable(9));
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