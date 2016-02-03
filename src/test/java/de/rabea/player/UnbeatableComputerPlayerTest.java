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
    public void createsTrapWhenPossibleOn3x3() {
        Board board = new Board(X,O, EMPTY,
                                X,EMPTY,EMPTY,
                                O,EMPTY,X);
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveOn3x3() {
        Board board = new Board(O,EMPTY,X,
                                X,X, EMPTY,
                                O,EMPTY,EMPTY);
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveEarlyOn3x3() {
        Board board = new Board(EMPTY,EMPTY,X,
                                EMPTY,X, EMPTY,
                                EMPTY,EMPTY,O);
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void makesWinningMoveOn4x4() {
        Board board = new Board(X, O, X, O,
                X, O, X, O,
                EMPTY, O, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(13, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveOn4x4() {
        Board board = new Board(X, O, X, O,
                                X, O, X, O,
                                X, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(12, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveWithAllEmptySlotsOn4x4() {
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
    @Test(timeout=2999)
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