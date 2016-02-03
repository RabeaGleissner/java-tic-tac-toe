package de.rabea.ui;

import de.rabea.game.Board;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;

public class PrettyBoardPainterTest {

    @Test
    public void displays3x3BoardWithMarksInDifferentColoursAndDashedLines() {
        Board board = new Board(3);
        board.placeMark(1, X);
        board.placeMark(3, O);
        PrettyBoardPainter prettyBoardPainter = new PrettyBoardPainter();
        assertEquals(
                  "\n|  1 |  \u001B[34mX\u001B[0m |  3 | \n" +
                  " --------------\n" +
                  "|  \u001B[31mO\u001B[0m |  5 |  6 | \n" +
                  " --------------\n" +
                  "|  7 |  8 |  9 |\n",

                prettyBoardPainter.drawBoard(board)
        );
    }

    @Test
    public void displays4x4BoardWithColourfulMarksAndDashedLines() {
        Board board = new Board(4);
        board.placeMark(1, X);
        board.placeMark(4, O);
        board.placeMark(15,X);
        PrettyBoardPainter prettyBoardPainter = new PrettyBoardPainter();
        assertEquals(
                "\n|  1 |  \u001B[34mX\u001B[0m |  3 |  4 | \n" +
                        " -------------------\n" +
                        "|  \u001B[31mO\u001B[0m |  6 |  7 |  8 | \n" +
                        " -------------------\n" +
                        "|  9 | 10 | 11 | 12 | \n" +
                        " -------------------\n" +
                        "| 13 | 14 | 15 |  \u001B[34mX\u001B[0m |\n",

                prettyBoardPainter.drawBoard(board)
        );


    }
}