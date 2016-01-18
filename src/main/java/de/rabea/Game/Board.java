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

    public void placeMark(int position, Mark mark) {
        cells[position] = mark.convertToCell();
    }

    public boolean gameOver() {
        return isFull() || hasWinner();
    }

    public boolean hasWinner() {
        for (List<Integer> line : getAllLines()) {
            if (anyLineMatchesAWinningCombination(cells(), line)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return emptyCellsCount() == 0;
    }

    public boolean isPositionAvailable(int position) {
        return isValidPosition(position) && positionIsEmpty(cells[position]);
    }

    private boolean positionIsEmpty(Cell cell) {
        return cell != Cell.X && cell != Cell.O;
    }

    private boolean isValidPosition(int position) {
        return position >= 0 && position < cells.length;
    }

    public Mark switchMark(Mark mark) {
        if (mark == Mark.X) {
            return Mark.O;
        } else {
            return Mark.X;
        }
    }

    public List<Integer> emptyCells() {
        List<Integer> emptyCells = new ArrayList<Integer>();
        for (int i=0; i < cells.length; i++) {
            Cell cell  = cells[i];
            if (cell == EMPTY) {
                emptyCells.add(i);
            }
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
        int dimension = getDimension();
        List<Integer> lastCellsPerRow = new ArrayList<Integer>();
        for (int i = 1; i <= dimension; i++) {
            lastCellsPerRow.add(dimension * i - 1);
        }
        return lastCellsPerRow;
    }

    public int getDimension() {
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
            if (cell == cellIndex && isNotTheLastCell(cell)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotTheLastCell(int cell) {
        return !(isLastCell(cell));
    }

    private boolean anyLineMatchesAWinningCombination(Cell[] gameState, List<Integer> combo) {
        return positionIsNotEmpty(gameState, combo) &&
                sameMarkInFirstAndSecondPosition(gameState, combo) &&
                sameMarkInSecondAndThirdPosition(gameState, combo);
    }

    private boolean positionIsNotEmpty(Cell[] gameState, List<Integer> combo) {
        return gameState[combo.get(0)]!= Cell.EMPTY;
    }

    private boolean sameMarkInFirstAndSecondPosition(Cell[] gameState, List<Integer> combo) {
        return gameState[combo.get(0)] == gameState[combo.get(1)];
    }

    private boolean sameMarkInSecondAndThirdPosition(Cell[] gameState, List<Integer> combo) {
        return gameState[combo.get(1)] == gameState[combo.get(2)];
    }

    public List<List<Integer>> getAllLines() {
        List<List<Integer>> allLines = new ArrayList<List<Integer>>();
        getRows(allLines);
        getColumns(allLines);
        getDiagonals(allLines);
        return allLines;
    }

    private void getDiagonals(List<List<Integer>> allLines) {
        for (List<Integer> diagonal : getDiagonals()) {
            allLines.add(diagonal);
        }
    }

    private void getColumns(List<List<Integer>> allLines) {
        for (List<Integer> column : getColumns()) {
            allLines.add(column);
        }
    }

    private void getRows(List<List<Integer>> allLines) {
        for (List<Integer> row : getRows()) {
            allLines.add(row);
        }
    }

    public List<List<Integer>> getRows() {
        List<List<Integer>> rows = new ArrayList<List<Integer>>();

        for (int i = 0; i < getDimension(); i++) {
           rows.add(getRow(i));
        }
        return rows;
    }

    public List<List<Integer>> getColumns() {
        List<List<Integer>> columns = new ArrayList<List<Integer>>();
        for (int i = 0; i < getDimension(); i++) {
            columns.add(getColumn(i));
        }
        return columns;
    }

    public List<List<Integer>> getDiagonals() {
        List<List<Integer>> diagonals = new ArrayList<List<Integer>>();
        List<Integer> forwardDiagonal = new ArrayList<Integer>();
        List<Integer> backwardDiagonal = new ArrayList<Integer>();

        List<List<Integer>> rows = getRows();
        for (int i = 0; i < getDimension(); i++) {
            forwardDiagonal.add(rows.get(i).get(i));
            backwardDiagonal.add(rows.get(i).get((getDimension() - 1) -i));
        }
        diagonals.add(forwardDiagonal);
        diagonals.add(backwardDiagonal);

        return diagonals;
    }

    private List<Integer> getRow(int index) {
        List<Integer> row = new ArrayList<Integer>();
        int start = index * getDimension();
        int end = start + getDimension();
        for (int i= start; i < end; i++) {
           row.add(i);
        }
        return row;
    }

    private List<Integer> getColumn(int index) {
        List<Integer> column = new ArrayList<Integer>();
        for (List<Integer> row : getRows()) {
            column.add(row.get(index));
        }
        return column;
    }
}
