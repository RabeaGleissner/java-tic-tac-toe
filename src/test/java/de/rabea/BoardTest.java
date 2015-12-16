package de.rabea;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.Cell.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;


public class BoardTest {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void emptyBoard() {
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
}