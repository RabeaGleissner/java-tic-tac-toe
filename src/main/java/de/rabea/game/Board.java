package de.rabea.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static de.rabea.game.Mark.*;

public class Board {

    private Mark[] cells;
    private int size;
    private int numberOfCells;

    public Board(Mark... initialGridLayout) {
        this.size = (int) Math.sqrt(initialGridLayout.length);
        this.numberOfCells = size * size;
        this.cells = initialGridLayout;
    }

    public Board(int size) {
        this.size = size;
        this.numberOfCells = size * size;
        this.cells = createCells();
    }

    private Mark[] createCells() {
        Mark[] createCells = new Mark[numberOfCells];
        for (int i = 0; i < numberOfCells; i++) {
            createCells[i] = EMPTY;
        }
        return createCells;
    }

    public Mark[] cells() {
        return cells;
    }

    public void placeMark(int position, Mark mark) {
        cells[position] = mark;
    }

    public Board placeMarkOnNewBoard(int position, Mark mark, Board board) {
        Mark[] gameStateCopy = board.cells().clone();
        gameStateCopy[position] = mark;
        return new Board(gameStateCopy);
    }

    public boolean gameOver() {
        return isFull() || hasWinner();
    }

    public boolean isDrawn() {
        return isFull() && !hasWinner();
    }

    public boolean hasWinner() {
        return getAllLines().stream().anyMatch(line -> line.hasWinner());
    }

    public boolean isFull() {
        return emptyCellsCount() == 0;
    }

    public boolean isPositionAvailable(int position) {
        return emptyCells().contains(position);
    }

    public List<Integer> emptyCells() {
        List<Integer> emptyCells = new ArrayList<>();
        for (int i=0; i < cells.length; i++) {
            Mark cell  = cells[i];
            if (cell == EMPTY) {
                emptyCells.add(i);
            }
        }
        return emptyCells;
    }

    private int emptyCellsCount() {
        int count = 0;

        for (Mark cell : cells) {
            if (cell == EMPTY) {
                count++;
            }
        }
        return count;
    }

    public List<Integer> indexOfLastCellPerRow() {
        int dimension = getSize();
        List<Integer> lastCellsPerRow = new ArrayList<>();
        for (int i = 1; i <= dimension; i++) {
            lastCellsPerRow.add(dimension * i - 1);
        }
        return lastCellsPerRow;
    }

    public int getSize() {
        return size;
    }

    public boolean is3x3() {
        return size == 3;
    }

    public boolean is4x4() {
        return size == 4;
    }

    public int indexOfLastCell() {
        return cells().length - 1;
    }

    public boolean isIndexOfLastCell(int cell) {
        return cell == indexOfLastCell();
    }

    public boolean isIndexOfEndOfRowExceptLastRow(int cell) {
        for (int cellIndex : indexOfLastCellPerRow()) {
            if (cell == cellIndex && isNotLastCellIndex(cell)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotLastCellIndex(int cell) {
        return !(isIndexOfLastCell(cell));
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

        for (int i = 0; i < getSize(); i++) {
           rows.add(getRow(i));
        }
        return rows;
    }

    private Line getRow(int currentIndex) {
        int index = currentIndex * size;
        Mark[] row = new Mark[size];
        for (int i = 0; i < size; i++) {
            row[i] = cells[index + i];
        }
        return new Line(row);
    }

    public List<Line> getColumns() {
        List<Line> columns = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            columns.add(getColumn(i));
        }
        return columns;
    }

    private Line getColumn(int index) {
        Mark[] column = new Mark[size];
        for (int i = 0; i < size; i++) {
            column[i] = cells[index + (size*i)];
        }
        return new Line(column);
    }

    public List<Line> getDiagonals() {
        Mark[] first= new Mark[size];
        Mark[] second = new Mark[size];
        for (int i = 0; i < size; i++) {
            first[i] = cells[(size * i) + i];
        }
        int offset = size - 1;
        for (int i = 0; i < size; i++) {
            second[i] = cells[offset];
            offset += size - 1;
        }
        return Arrays.asList(new Line(first), new Line(second));
    }

    public Mark winningPlayerMark() {
        Optional<Line> winningLine = getAllLines().stream().filter(Line::hasWinner).findFirst();
        if (winningLine.isPresent()) {
            return winningLine.get().firstMarkInLine();
        }
        return null;
    }
}
