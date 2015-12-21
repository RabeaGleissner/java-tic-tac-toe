package de.rabea.game;

import static de.rabea.game.Cell.*;

public class Board {

    private Cell[] cells;

    public Board() {
        Cell emptySlot = EMPTY;
        this.cells = new Cell[] {emptySlot, emptySlot, emptySlot,
                emptySlot, emptySlot, emptySlot,
                emptySlot, emptySlot, emptySlot};
    }

    public Cell[] returnCells() {
        return cells;
    }

    public Cell[] placeMark(int position, Cell mark) {
        cells[position] = mark;
        return cells;
    }

    public boolean isFull() {
        return emptyCellsCount() == 0;
    }

    public boolean isChosenPositionAvailable(int position) {
        return !(cells[position] == Cell.X || cells[position] == Cell.O);
    }

    private int emptyCellsCount() {
        int count = 0;

        for (Cell cell : cells) {
            if (cell == EMPTY) {
                count++;
            }
        }
        return count;
    }

    public Cell switchMark(Cell mark) {
        if (mark == Cell.X) {
            return Cell.O;
        } else {
            return Cell.X;
        }
    }
}
