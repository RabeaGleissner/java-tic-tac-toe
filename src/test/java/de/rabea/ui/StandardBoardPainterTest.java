package de.rabea.ui;

import de.rabea.game.Board;
import org.junit.Ignore;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.assertj.core.api.Assertions.assertThat;

public class StandardBoardPainterTest {

    @Test
    public void displaysEmpty3x3Board() {
        Board board = new Board(3);
        StandardBoardPainter standardBoardPainter = new StandardBoardPainter();
        assertThat(standardBoardPainter.drawBoard(board)).isEqualTo(
                "\n" +
                        "1 2 3 \n" +
                        "4 5 6 \n" +
                        "7 8 9 "
        );
    }

    @Test
    public void displays3x3BoardWithMarks() {
        Board board = new Board(3);
        StandardBoardPainter standardBoardPainter = new StandardBoardPainter();
        board.placeMark(1, X);
        board.placeMark(3, O);
        assertThat(standardBoardPainter.drawBoard(board)).isEqualTo(
                "\n" +
                        "1 X 3 \n" +
                        "O 5 6 \n" +
                        "7 8 9 "
        );
    }

}