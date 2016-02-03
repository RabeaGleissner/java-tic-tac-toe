package de.rabea.player;

import de.rabea.game.Board;
import org.junit.Ignore;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnbeatableComputerPlayerTest {
    UnbeatableComputerPlayer unbeatableComputerPlayer = new UnbeatableComputerPlayer(O);

    @Test
    public void playsOnlyAvailablePosition() {
        Board board = new Board(X,O,X,
                                O,X,O,
                                O,X,EMPTY);
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void playsBlockingMove() {
        Board board = new Board(O, X,EMPTY,
                                EMPTY,X,O,
                                EMPTY,EMPTY,EMPTY);
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void createsTrapWhenPossible() {
        Board board = new Board(X,O, EMPTY,
                                X,EMPTY,EMPTY,
                                O,EMPTY,X);
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void placesMarkInOneOfTheEmptyPositions() {
        Board board = new Board(X, O,X,
                                EMPTY,O,EMPTY,
                                EMPTY,X,EMPTY);
        assertTrue(possibleMovesThreeFiveSixEight(unbeatableComputerPlayer.getPosition(board)));
    }
    private boolean possibleMovesThreeFiveSixEight(int position) {
        return position == 3 || position == 5 || position == 6 || position == 8;
    }

    @Test
    public void blocksOpponentsWinningMove() {
        Board board = new Board(O,EMPTY,X,
                                X,X, EMPTY,
                                O,EMPTY,EMPTY);
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveEarlyInTheGame() {
        Board board = new Board(EMPTY,EMPTY,X,
                                EMPTY,X, EMPTY,
                                EMPTY,EMPTY,O);
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksTheOpponentsAttemptToSetATrap() {
        Board board = new Board(X, EMPTY,EMPTY,
                                EMPTY,O, EMPTY,
                                EMPTY,EMPTY,X);
        assertTrue(doesNotHelpOpponentToSetATrap(unbeatableComputerPlayer.getPosition(board)));
    }
    private boolean doesNotHelpOpponentToSetATrap(int position) {
        return !(position == 2 || position == 6);
    }

    @Test
    public void blocksOpponentsWinningMoveWithTwoMovesLeft() {
        Board board = new Board(O,X, X,
                                X,X, O,
                                O,EMPTY,EMPTY);
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksAnOpponentsWinWithFourMovesLeft() {
        Board board = new Board(X, EMPTY,X,
                                O, EMPTY,X,
                                EMPTY, EMPTY,O);
        assertEquals(1, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveOn4x4Board() {
        Board board = new Board(X, O, X, O,
                                X, O, X, O,
                                X, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(12, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void makesWinningMoveOn4x4Board() {
        Board board = new Board(X, O, X, O,
                                X, O, X, O,
                                EMPTY, O, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(13, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveWithAllEmptySlots4x4() {
        Board board = new Board(X, X, X, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(3, unbeatableComputerPlayer.getPosition(board));
    }


    @Ignore
    @Test(timeout=3200)
    public void measureSpeedOfFirstMoveOn3x3Board() {
        UnbeatableComputerPlayer computer = new UnbeatableComputerPlayer(X);
        Board board;
        long startTime = System.nanoTime();
        for (int i = 0; i <= 10; i++) {
            board = new Board(3);
            computer.getPosition(board);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("duration in milliseconds= " + duration);
        assertTrue(true);
    }

    @Ignore
    @Test
    public void measureSpeedOfFirstMoveOn4x4Board() {
        UnbeatableComputerPlayer computer = new UnbeatableComputerPlayer(X);
        Board board;
        long startTime = System.nanoTime();
            board = new Board(4);
            computer.getPosition(board);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("duration in milliseconds= " + duration);
        assertTrue(true);
    }
}