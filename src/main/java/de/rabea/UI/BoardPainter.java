package de.rabea.ui;

import de.rabea.game.Cell;

import static de.rabea.game.Cell.*;

public class BoardPainter {
    private String blueColourForX = "\u001B[34m";
    private String redColourForO = "\u001B[31m";
    private String colourReset = "\u001B[0m";

    public String drawBoard(Cell[] cells) {
        int i = 0;
        String boardImage= "\n";
        for (Enum cell : cells) {
            i ++;
            boardImage = printSymbolInCell(i, boardImage, cell);
            boardImage = printHorizontalLines(i, boardImage);
            boardImage = printLastPipe(i, boardImage);
        }
        return boardImage;
    }

    private String printSymbolInCell(int i, String boardImage, Enum cell) {
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
        if (endOfFirstOrSecondRow(i)) {
            boardImage += horizontalLine();
        }
        return boardImage;
    }

    private String printLastPipe(int i, String boardImage) {
        if (endOfLastRow(i)) {
            boardImage += lastPipe();
        }
        return boardImage;
    }

    private boolean endOfLastRow(int i) {
        return i == 9;
    }

    private boolean endOfFirstOrSecondRow(int i) {
        return i == 3 || i == 6;
    }

    private String colouredO(Enum cell) {
        return redColourForO + cell.toString() + colourReset;
    }

    private String colouredX(Enum cell) {
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
}
