package de.rabea.game;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.rabea.game.Mark.*;
import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class BoardTest {

    @Test
    public void boardIsEmpty3x3() {
        Board emptyBoard = new Board(3);
        assertFalse(emptyBoard.isFull());
    }

    @Test
    public void boardIsFull3x3() {
        Board fullBoard = full3x3BoardNoWinner();
        assertTrue(fullBoard.isFull());
    }

    @Test
    public void isChosenPositionStillFreeOn3x3() {
        Board emptyBoard = new Board(3);
        assertEquals(true, emptyBoard.isPositionAvailable(1));
    }

    @Test
    public void markedPositionIsNoLongerAvailableOn3x3() {
        Board board = new Board(3);
        board.placeMarkOnExistingBoard(1, X);
        assertFalse(board.isPositionAvailable(1));
    }

    @Test
    public void markedPositionIsNoLongerAvailableOn4x4() {
        Board board = new Board(4);
        board.placeMarkOnExistingBoard(1, X);
        assertFalse(board.isPositionAvailable(1));
    }

    @Test
    public void knowsWhenPositionIsUnavailableOn3x3() {
        Board board = new Board(3);
        int positionNotOnBoard = 9;
        assertFalse(board.isPositionAvailable(positionNotOnBoard));
    }

    @Test
    public void knowsWhenPositionIsAvailableOn4x4() {
        Board board = new Board(4);
        int positionOnBoard = 9;
        assertTrue(board.isPositionAvailable(positionOnBoard));
    }

    @Test
    public void listsAllEmptyPositionsFor3x3() {
        Board board = new Board(3);
        board.placeMarkOnExistingBoard(1, X);
        board.placeMarkOnExistingBoard(2, O);
        board.placeMarkOnExistingBoard(3, X);
        board.placeMarkOnExistingBoard(6, O);
        assertEquals(asList(0, 4, 5, 7, 8), board.emptyCells());
    }

    @Test
    public void listsAllEmptyPositionsFor4x4() {
        Board board = new Board(4);
        board.placeMarkOnExistingBoard(1, X);
        board.placeMarkOnExistingBoard(2, O);
        board.placeMarkOnExistingBoard(3, X);
        board.placeMarkOnExistingBoard(6, O);
        List<Integer> emptyCells = asList(0, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertEquals(emptyCells, board.emptyCells());
    }

    @Test
    public void indexOfLastCellInEachRowOn3x3() {
        Board board = new Board(3);
        List<Integer> cells = new ArrayList<>(asList(2, 5, 8));
        assertEquals(cells, board.indexOfLastCellPerRow());
    }

    @Test
    public void indexOfLastCellOfBoardOn3x3() {
        Board board = new Board(3);
        assertEquals(8, board.indexOfLastCell());
    }

    @Test
    public void widthAndHeightOf3x3() {
        Board board = new Board(3);
        assertEquals(3, board.getDimension());
    }

    @Test
    public void isEndOfLastRowFor3x3() {
        Board board = new Board(3);
        assertTrue(board.isIndexOfLastCell(8));
        assertFalse(board.isIndexOfLastCell(7));
    }

    @Test
    public void isEndOfLastRowFor4x4() {
        Board board = new Board(4);
        assertTrue(board.isIndexOfLastCell(15));
        assertFalse(board.isIndexOfLastCell(7));
    }

    @Test
    public void gameOverWithoutWinnerOn3x3() {
        Board board = full3x3BoardNoWinner();
        assertTrue(board.gameOver());
        assertFalse(board.hasWinner());
    }

    @Test
    public void gameOverWithoutWinnerOn4x4() {
        Board board = full4x4BoardNoWinner();
        assertTrue(board.gameOver());
        assertFalse(board.hasWinner());
    }

    @Test
    public void gameOverWithWinnerOn3x3() {
        Board board = horizontalsWinning3x3Board();
        assertTrue(board.gameOver());
    }

    @Test
    public void gameOverWithWinnerOn4x4() {
        Board board = horizontalsWinning4x4Board();
        assertTrue(board.gameOver());
    }

    @Test
    public void verticalWinningCombinationOn3x3() {
        Board board = verticalsWinning3x3Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void verticalWinningCombinationOn4x4() {
        Board board = verticalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void horizontalWinningCombinationOn3x3() {
        Board board = horizontalsWinning3x3Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void horizontalWinningCombinationOn4x4() {
        Board board = horizontalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void diagonalWinningCombinationOn3x3() {
        Board board = diagonalsWinning3x3Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void diagonalWinningCombinationOn4x4() {
        Board board = diagonalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void playerXIsWinnerOn3x3() {
        Board boardWithWinnerX = verticalsWinning3x3Board();
        assertEquals(X, boardWithWinnerX.winningPlayerMark());
    }

    @Test
    public void gameIsDrawnOn3x3() {
        Board drawnGameBoard = full3x3BoardNoWinner();
        assertTrue(drawnGameBoard.isDrawn());
    }

    @Test
    public void nullWhenNoWinnerOn3x3() {
        Board fullBoardNoWinner = full3x3BoardNoWinner();
        assertEquals(null, fullBoardNoWinner.winningPlayerMark());
    }

    @Test
    public void placesMarkOnNew3x3Board() {
        Board board = new Board(3);
        board.placeMarkOnNewBoard(1, X, board);
        assertEquals(9, board.emptyCells().size());
    }

    @Test
    public void placesMarkOnNew4x4Board() {
        Board board = new Board(4);
        board.placeMarkOnNewBoard(1, X, board);
        assertEquals(16, board.emptyCells().size());
    }

    @Test
    public void knowsThatThereIsAWinnerOn4x4() {
        Board board = backwardsDiagonalsWinning4x4Board();
        assertTrue(board.hasWinner());
    }

    @Test
    public void mapsPositionsOverToMarks() {
        Board board = new Board(3);

        Map<Integer, Mark> marks = board.cellsWithIndex();
        for (Map.Entry<Integer, Mark> pair : marks.entrySet()) {
            assertTrue(pair.getValue() == EMPTY);
        }
    }

    private Board full3x3BoardNoWinner() {
        return new Board(
                X, X, O,
                O, O, X,
                X, O, X);
    }
    private Board full4x4BoardNoWinner() {
        return new Board(
                X, X, O, O,
                O, O, X, X,
                X, O, X, O,
                X, O, X, O);
    }
    private Board horizontalsWinning3x3Board() {
        return new Board(
                X, X, X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, O, O);
    }
    private Board horizontalsWinning4x4Board() {
        return new Board(
                X, X, X, X,
                EMPTY, EMPTY, EMPTY,O,
                EMPTY, O, O, O,
                X, O, X, O);
    }
    private Board verticalsWinning3x3Board() {
        return new Board(
                X, X, O,
                X, O, O,
                X, O, X);
    }
    private Board verticalsWinning4x4Board(){
        return new Board(X, X, O, EMPTY,
                         X, O, O, EMPTY,
                         X, O, X, O,
                         X, EMPTY, EMPTY, EMPTY);
    }
    private Board diagonalsWinning3x3Board() {
        return new Board(
                X, X, O,
                EMPTY, X, EMPTY,
                EMPTY, O, X);
    }
    private Board diagonalsWinning4x4Board() {
        return new Board(
                X,     X,     O,     O,
                EMPTY, X,     EMPTY, O,
                EMPTY, O,     X,     O,
                EMPTY, EMPTY, EMPTY, X);
    }
    private Board backwardsDiagonalsWinning4x4Board() {
        return new Board(
                X, O, O, X,
                O, X, X, O,
                O, X, EMPTY, EMPTY,
                X, EMPTY, EMPTY, EMPTY);
    }
}