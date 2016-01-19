package de.rabea.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.rabea.game.Mark.EMPTY;

public class Board {

    private Mark[] cells;


    public Board() {
       this(new Mark[] {EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY});
    }

    public Board(Mark[] cells) {
       this.cells = cells;
    }

    public Mark[] cells() {
        return cells;
    }

    public void placeMark(int position, Mark mark) {
        cells[position] = mark;
    }

    public boolean gameOver() {
        return isFull() || hasWinner();
    }

    public boolean hasWinner() {
        return getAllLines().stream().anyMatch(line -> line.hasWinner());
    }

    public boolean isFull() {
        return emptyCellsCount() == 0;
    }

    public boolean isPositionAvailable(int position) {
        return isValidPosition(position) && positionIsEmpty(cells[position]);
    }

    private boolean positionIsEmpty(Mark cell) {
        return cell != Mark.X && cell != Mark.O;
    }

    private boolean isValidPosition(int position) {
        return position >= 0 && position < cells.length;
    }

    public List<Integer> emptyCells() {
        List<Integer> emptyCells = new ArrayList<>();
        for (int i=0; i < cells.length; i++) {
            Mark cell  = cells[i];
            if (cell == Mark.EMPTY) {
                emptyCells.add(i);
            }
        }
        return emptyCells;
    }

    private int emptyCellsCount() {
        int count = 0;

        for (Mark cell : cells) {
            if (cell == Mark.EMPTY) {
                count++;
            }
        }
        return count;
    }

    public List<Integer> indexOfLastCellPerRow() {
        int dimension = getDimension();
        List<Integer> lastCellsPerRow = new ArrayList<>();
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

    public List<Line> getAllLines() {
        List<Line> allLines = new ArrayList<>();
        allLines.addAll(getRows());
        allLines.addAll(getColumns());
        allLines.addAll(getDiagonals());
        return allLines;
    }

    public List<Line> getRows() {
        List<Line> rows = new ArrayList<>();

        for (int i = 0; i < getDimension(); i++) {
           rows.add(getRow(i));
        }
        return rows;
    }

    private Line getRow(int index) {
        int start = index * 3;
        return new Line(cells[start], cells[start+1], cells[start+2]);
    }

    public List<Line> getColumns() {
        List<Line> columns = new ArrayList<>();
        for (int i = 0; i < getDimension(); i++) {
            columns.add(getColumn(i));
        }
        return columns;
    }

    private Line getColumn(int index) {
        return new Line(cells[index], cells[index+3], cells[index+6]);
    }

    public List<Line> getDiagonals() {
        Line firstDiagonal = new Line(cells[0], cells[4], cells[8]);
        Line secondDiagonal = new Line(cells[2], cells[4], cells[6]);
        return Arrays.asList(firstDiagonal, secondDiagonal);
    }
}
