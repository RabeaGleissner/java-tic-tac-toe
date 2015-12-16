package de.rabea;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class BoardTest {

    private Board board;
    private String emptySlot = "_";

    @Before

    public void setup() {
    board = new Board();
    }

    @Test
    public void emptyBoard() {
        String[] emptyBoard = {emptySlot, emptySlot, emptySlot,
                               emptySlot, emptySlot, emptySlot,
                               emptySlot, emptySlot, emptySlot};
        assertArrayEquals(emptyBoard, board.returnCells());
    }

}