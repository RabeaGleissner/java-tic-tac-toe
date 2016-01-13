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

    public List<Integer> indexOfLastCellPerRow() {
        int length = getLengthOfRow();
        List<Integer> lastCellsPerRow = new ArrayList<Integer>();
        for (int i = 1; i <= length; i ++) {
            lastCellsPerRow.add(length * i - 1);
        }
        return lastCellsPerRow;
    }

    private int getLengthOfRow() {
        double length = (double) cells().length;
        double squareRoot = Math.sqrt(length);
        return (int) squareRoot;
    }

    public int indexOfLastCell() {
        return cells().length - 1;
    }

    public boolean isLastCell(int cell) {
        return cell == indexOfLastCell();
    }

    public boolean isEndOfFirstOrSecondRow(int cell) {
        for (int cellIndex : indexOfLastCellPerRow()) {
            if (cell == cellIndex && !(isLastCell(cell))) {
                return true;
            }
        }
        return false;
    }
}
