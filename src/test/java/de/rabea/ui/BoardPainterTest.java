package de.rabea.ui;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;

public class BoardPainterTest {
    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }


    @Test
    public void displaysBoardWithMarks() {
        board.placeMark(1, X);
        board.placeMark(3, O);
        BoardPainter boardPainter = new BoardPainter(board);
        assertEquals(
                  clearScreen() +
                  "\n| 1 | \u001B[34mX\u001B[0m | 3 | \n" +
                  " -----------\n" +
                  "| \u001B[31mO\u001B[0m | 5 | 6 | \n" +
                  " -----------\n" +
                  "| 7 | 8 | 9 |\n",

                boardPainter.drawBoard()
        );
    }

    private String clearScreen() {
        return "\033[H\033[2J";
    }
}