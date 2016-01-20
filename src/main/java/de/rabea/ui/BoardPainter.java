package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

import static de.rabea.game.Mark.EMPTY;
import static de.rabea.game.Mark.X;

public class BoardPainter {
    private String blueColourForX = "\u001B[34m";
    private String redColourForO = "\u001B[31m";
    private String colourReset = "\u001B[0m";
    private String clearScreen= "\033[H\033[2J";
    private Board board;

    public BoardPainter(Board board) {
        this.board = board;
    }

    public String drawBoard() {
        int i = 0;
        String boardImage= clearScreen() + "\n";
        for (Mark cell : board.cells()) {
            i++;
            boardImage = printSymbolInCell(i, boardImage, cell);
            boardImage = printHorizontalLines(i, boardImage);
            boardImage = printLastPipe(i, boardImage);
        }
        return boardImage;
    }

    private String printSymbolInCell(int i, String boardImage, Mark cell) {
        if (cell == EMPTY) {
            boardImage += pipe() + i;
        } else if (cell == X) {
            boardImage += pipe() + colouredX(cell);
        } else {
            boardImage += pipe() + colouredO(cell);
        }
        boardImage += space();
        return boardImage;
    }

    private String printHorizontalLines(int i, String boardImage) {
        if (board.isEndOfFirstOrSecondRow(i-1)) {
            boardImage += horizontalLine();
        }
        return boardImage;
    }

    private String printLastPipe(int i, String boardImage) {
        if (board.isLastCell(i-1)) {
            boardImage += lastPipe();
        }
        return boardImage;
    }

    private String colouredO(Mark cell) {
        return redColourForO + cell.toString() + colourReset;
    }

    private String colouredX(Mark cell) {
        return blueColourForX + cell.toString() + colourReset;
    }

    private String horizontalLine() {
        return "| \n -----------\n";
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

    public String clearScreen() {
        return clearScreen;
    }
}
