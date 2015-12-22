package de.rabea.game;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Cell.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


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
        assertThat(board.placeMark(1, X)).isEqualTo(cells);
    }

    @Test
    public void boardIsEmpty() {
        assertThat(board.isFull()).isFalse();
    }

    @Test
    public void boardIsFull() {
        fillUpBoard();
        assertThat(board.isFull()).isTrue();
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