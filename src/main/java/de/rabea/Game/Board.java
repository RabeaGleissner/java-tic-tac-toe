package de.rabea.game;

import java.util.ArrayList;
import java.util.List;

import static de.rabea.game.Cell.EMPTY;

public class Board {

    private Cell[] cells;
    private final int[][] WINNING_COMBINATIONS = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

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

    public boolean gameOver() {
        return isFull() || hasWinner();
    }

    public boolean hasWinner() {
        for (int[] combo : WINNING_COMBINATIONS) {
            if (anyLineMatchesAWinningCombination(cells(), combo)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return emptyCellsCount() == 0;
    }

    public boolean isPositionAvailable(int position) {
        return position >= 0 && position < cells.length &&
                !(cells[position] == Cell.X || cells[position] == Cell.O);
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

    private int emptyCellsCount() {
        int count = 0;

        for (Cell cell : cells) {
            if (cell == EMPTY) {
                count++;
            }
        }
        return count;
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

    private boolean anyLineMatchesAWinningCombination(Cell[] gameState, int[] combo) {
        return positionIsNotEmpty(gameState, combo) &&
                sameMarkInFirstAndSecondPosition(gameState, combo) &&
                sameMarkInSecondAndThirdPosition(gameState, combo);
    }

    private boolean positionIsNotEmpty(Cell[] gameState, int[] combo) {
        return gameState[combo[0]]!= Cell.EMPTY;
    }

    private boolean sameMarkInFirstAndSecondPosition(Cell[] gameState, int[] combo) {
        return gameState[combo[0]] == gameState[combo[1]];
    }

    private boolean sameMarkInSecondAndThirdPosition(Cell[] gameState, int[] combo) {
        return gameState[combo[1]] == gameState[combo[2]];
    }
}
