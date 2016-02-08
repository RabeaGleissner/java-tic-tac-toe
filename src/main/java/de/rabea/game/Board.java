package de.rabea.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static de.rabea.game.Mark.*;

public class Board {

    private Mark[] cells;
    private int dimension;
    private int numberOfCells;

    public Board(Mark... gameState) {
        this.dimension = (int) Math.sqrt(gameState.length);
        this.numberOfCells = gameState.length;
        this.cells = gameState;
    }

    public Board(int dimension) {
        this.dimension = dimension;
        this.numberOfCells = dimension * dimension;
        this.cells = createCells();
    }

    private Mark[] createCells() {
        Mark[] cells = new Mark[numberOfCells];
        for (int i = 0; i < numberOfCells; i++) {
            cells[i] = EMPTY;
        }
        return cells;
    }

    public Mark[] cells() {
        return cells;
    }

    public void placeMarkOnExistingBoard(int position, Mark mark) {
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
        for (int i = 0; i < cells.length; i++) {
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
        int dimension = getDimension();
        List<Integer> lastCellsPerRow = new ArrayList<>();
        for (int i = 1; i <= dimension; i++) {
            lastCellsPerRow.add(dimension * i - 1);
        }
        return lastCellsPerRow;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean hasDimensionOf(int size) {
        return dimension == size;
    }

    public int indexOfLastCell() {
        return cells().length - 1;
    }

    public boolean isIndexOfLastCell(int cell) {
        return cell == indexOfLastCell();
    }

    public boolean isEndOfRowIndexExceptLastRow(int cell) {
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

    private List<Line> getRows() {
        List<Line> rows = new ArrayList<>();

        for (int i = 0; i < getDimension(); i++) {
           rows.add(getRow(i));
        }
        return rows;
    }

    private Line getRow(int currentIndex) {
        int index = currentIndex * dimension;
        Mark[] row = new Mark[dimension];
        for (int i = 0; i < dimension; i++) {
            row[i] = cells[index + i];
        }
        return new Line(row);
    }

    private List<Line> getColumns() {
        List<Line> columns = new ArrayList<>();
        for (int i = 0; i < getDimension(); i++) {
            columns.add(getColumn(i));
        }
        return columns;
    }

    private Line getColumn(int index) {
        Mark[] column = new Mark[dimension];
        for (int i = 0; i < dimension; i++) {
            column[i] = cells[index + (dimension *i)];
        }
        return new Line(column);
    }

    private List<Line> getDiagonals() {
        Mark[] first= new Mark[dimension];
        Mark[] second = new Mark[dimension];
        for (int i = 0; i < dimension; i++) {
            first[i] = cells[(dimension * i) + i];
        }
        int offset = dimension - 1;
        for (int i = 0; i < dimension; i++) {
            second[i] = cells[offset];
            offset += dimension - 1;
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
