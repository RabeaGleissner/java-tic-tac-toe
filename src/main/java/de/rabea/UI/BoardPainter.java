package de.rabea.ui;

import de.rabea.game.Cell;

public class BoardPainter {
    private String blueColourForX = "\u001B[34m";
    private String redColourForO = "\u001B[31m";
    private String colourReset = "\u001B[0m";

    public String drawBoard(Cell[] cells) {
        String boardImage= "";
        int i = 0;
        boardImage += "\n";
        for (Enum cell : cells) {
            i ++;

            if (cell == Cell.EMPTY) {
                boardImage += pipe() + i;
            } else if (cell == Cell.X) {
                boardImage += pipe() + colouredX(cell);
            } else {
                boardImage += pipe() + colouredO(cell);
            }
            boardImage += space();

            if (endOfFirstOrSecondRow(i)) {
                boardImage += horizontalLine();

            }
            if (endOfLastRow(i)) {
                boardImage += lastPipe();
            }
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
