package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

import static de.rabea.game.Mark.EMPTY;
import static de.rabea.game.Mark.X;

public class PrettyBoardPainter implements BoardPainter {
    private final String BLUE_COLOUR_FOR_X = " \u001B[34m";
    private final String RED_COLOUR_FOR_O = " \u001B[31m";
    private final String COLOUR_RESET = "\u001B[0m";

    @Override
    public String drawBoard(Board board) {
        int i = 0;
        String boardImage = "\n";
        for (Mark cell : board.cells()) {
            i ++;
            boardImage = printSymbolInCell(i, boardImage, cell);
            boardImage = printHorizontalLines(i, boardImage, board);
            boardImage = printLastPipe(i, boardImage, board);
        }
        return boardImage;
    }

    private String printSymbolInCell(int i, String boardImage, Mark cell) {
        if (cell == EMPTY) {
            boardImage += pipe() + String.format("%2d", i);
        } else if (cell == X) {
            boardImage += pipe() + colouredX(cell);
        } else {
            boardImage += pipe() + colouredO(cell);
        }
        boardImage += space();
        return boardImage;
    }

    private String printHorizontalLines(int i, String boardImage, Board board) {
        if (board.isIndexOfEndOfRowExceptLastRow(i-1) && board.is3x3()) {
            boardImage += horizontalLineFor3x3();
        } else if (board.isIndexOfEndOfRowExceptLastRow(i-1) && board.is4x4()) {
            boardImage += horizontalLineFor4x4();
        }
        return boardImage;
    }

    private String printLastPipe(int i, String boardImage, Board board) {
        if (board.isIndexOfLastCell(i-1)) {
            boardImage += lastPipe();
        }
        return boardImage;
    }

    private String colouredO(Mark cell) {
        return RED_COLOUR_FOR_O + cell.toString() + COLOUR_RESET;
    }

    private String colouredX(Mark cell) {
        return BLUE_COLOUR_FOR_X + cell.toString() + COLOUR_RESET;
    }

    private String horizontalLineFor3x3() {
        return "| \n --------------\n";
    }

    private String horizontalLineFor4x4() {
        return "| \n -------------------\n";
    }

    private String space() {
        return " ";
    }

    private String pipe() {
        return "| ";
    }

    private String lastPipe() {
        return "|\n";
    }

}
