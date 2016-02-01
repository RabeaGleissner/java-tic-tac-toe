package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

import static de.rabea.game.Mark.EMPTY;
import static de.rabea.game.Mark.X;

public class StandardBoardPainter implements BoardPainter {

    @Override
    public String drawBoard(Board board) {
        String boardImage = "\n";
        int i = 0;
        for (Mark cell : board.cells()) {
            i++;
            if (cell == EMPTY) {
                boardImage += i;
                boardImage += " ";
            } else if (cell == X) {
                boardImage += "X ";
            } else {
                boardImage += "O ";
            }
            if (i == 3 || i == 6) {
                boardImage += "\n";
            }
        }
        return boardImage;
    }


}
