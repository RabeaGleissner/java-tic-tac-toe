package de.rabea;

import static de.rabea.Cell.*;

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
}
