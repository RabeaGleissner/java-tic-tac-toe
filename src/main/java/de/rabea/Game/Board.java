package de.rabea.game;

import java.util.ArrayList;
import java.util.List;

import static de.rabea.game.Cell.EMPTY;

public class Board {

    private Cell[] cells;

    public Board() {
       this(new Cell[] {EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY});
    }

    public Board(Cell[] cells) {
       this.cells = cells;
    }

    public Cell[] cells() {
        return cells;
    }

    public Cell[] placeMark(int position, Cell mark) {
        cells[position] = mark;
        return cells;
    }

    public boolean isFull() {
        return emptyCellsCount() == 0;
    }

    public boolean isPositionAvailable(int position) {
        return position >= 0 && position < cells.length &&
                !(cells[position] == Cell.X || cells[position] == Cell.O);
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

    public List<Integer> emptyCells() {
        List<Integer> emptyCells = new ArrayList<Integer>();
        int i = 0;
        for (Cell cell : cells) {
            if (cell == EMPTY) {
               emptyCells.add(i);
            }
            i++;
        }
        return emptyCells;
    }
}
