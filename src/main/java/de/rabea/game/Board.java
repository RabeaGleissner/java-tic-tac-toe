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

    public int indexOfLastCell() {
        return cells().length - 1;
    }

    public boolean isIndexOfLastCell(int cell) {
        return cell == indexOfLastCell();
    }

    public boolean isIndexOfEndOfFirstOrSecondRow(int cell) {
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
        int index = currentIndex * getSize();
        if (size == 3) {
            return new Line(cells[index], cells[index+1], cells[index+2]);
        } else {

            return new Line(cells[index], cells[index+1], cells[index+2], cells[index+3]);
        }
    }

    public List<Line> getColumns() {
        List<Line> columns = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            columns.add(getColumn(i));
        }
        return columns;
    }

    private Line getColumn(int index) {
        if (size == 3) {
            return new Line(cells[index], cells[index+3], cells[index+6]);
        } else {
            return new Line(cells[index], cells[index+size], cells[index+(size*2)], cells[index+(size*3)]);
        }
    }

    public List<Line> getDiagonals() {
        if (size == 3) {
            Line firstDiagonal = new Line(cells[0], cells[4], cells[8]);
            Line secondDiagonal = new Line(cells[2], cells[4], cells[6]);
            return Arrays.asList(firstDiagonal, secondDiagonal);
        } else {
            Line firstDiagonal = new Line(cells[0], cells[5], cells[10], cells[15]);
            Line secondDiagonal = new Line(cells[3], cells[6], cells[9], cells[12]);
            return Arrays.asList(firstDiagonal, secondDiagonal);
        }
    }

    public Mark winningPlayerMark() {
        Optional<Line> winningLine = getAllLines().stream().filter(Line::hasWinner).findFirst();
        if (winningLine.isPresent()) {
            return winningLine.get().firstMarkInLine();
        }
        return null;
    }
}
