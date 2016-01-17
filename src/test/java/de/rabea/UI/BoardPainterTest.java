package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Cell;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Cell.*;
import static org.junit.Assert.assertEquals;

public class BoardPainterTest {
    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }


    @Test
    public void displaysBoardWithMarks() {
        Cell [] cells = {EMPTY, X, EMPTY, O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};
        BoardPainter boardPainter = new BoardPainter(board);
        assertEquals(
                  "[H [2J\n| 1 | \u001B[34mX\u001B[0m | 3 | \n" +
                  " -----------\n" +
                  "| \u001B[31mO\u001B[0m | 5 | 6 | \n" +
                  " -----------\n" +
                  "| 7 | 8 | 9 |\n",

                boardPainter.drawBoard(cells)
        );
    }

}