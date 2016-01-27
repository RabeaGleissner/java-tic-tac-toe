package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnbeatableComputerPlayerTest {
    UnbeatableComputerPlayer unbeatableComputerPlayer = new UnbeatableComputerPlayer(O);

    @Test
    public void playsOnlyAvailablePosition() {
        Board board = new Board(new Mark[] {X,O,X,
                                            O,X,O,
                                            O,X,EMPTY});
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void playsWinningMove() {
        Board board = new Board(new Mark[] {O,    X,EMPTY,
                                            EMPTY,X,O,
                                            EMPTY,EMPTY,EMPTY});
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void createsTrapWhenPossible() {
        Board board = new Board(new Mark[] {X,O,    EMPTY,
                                            X,EMPTY,EMPTY,
                                            O,EMPTY,X});
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void placesMarkInEmptyPosition() {
        Board board = new Board(new Mark[] {X,    O,X,
                                            EMPTY,O,EMPTY,
                                            EMPTY,X,EMPTY});
        assertTrue(possibleMovesThreeFiveSixEight(unbeatableComputerPlayer.getPosition(board)));
    }
    private boolean possibleMovesThreeFiveSixEight(int position) {
        return position == 3 || position == 5 || position == 6 || position == 8;
    }

    @Test
    public void blocksOpponentsWinningMove() {
        Board board = new Board(new Mark[] {O,EMPTY,X,
                                            X,X,    EMPTY,
                                            O,EMPTY,EMPTY});
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveEarlyInTheGame() {
        Board board = new Board(new Mark[] {EMPTY,EMPTY,X,
                                            EMPTY,X,    EMPTY,
                                            EMPTY,EMPTY,O});
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksTheOpponentsAttemptToSetATrap() {
        Board board = new Board(new Mark[] {X,    EMPTY,EMPTY,
                                            EMPTY,O,    EMPTY,
                                            EMPTY,EMPTY,X});
        assertTrue(doesNotHelpOpponentToSetATrap(unbeatableComputerPlayer.getPosition(board)));
    }
    private boolean doesNotHelpOpponentToSetATrap(int position) {
        return !(position == 2 || position == 6);
    }

    @Test
    public void blocksOpponentsWinningMoveWithTwoMovesLeft() {
        Board board = new Board(new Mark[] {O,X,    X,
                                            X,X,    O,
                                            O,EMPTY,EMPTY});
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksAnOpponentsWinWithFourMovesLeft() {
        Board board = new Board(new Mark[] {X,     EMPTY,X,
                                            O,     EMPTY,X,
                                            EMPTY, EMPTY,O});
        assertEquals(1, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void measureSpeedOfFirstMove() {
        UnbeatableComputerPlayer computer = new UnbeatableComputerPlayer(X);
        Board board;
        long startTime = System.nanoTime();
        for (int i = 0; i <= 10; i++) {
            board = new Board(new Mark[] {EMPTY,EMPTY,EMPTY,
                    EMPTY,EMPTY,EMPTY,
                    EMPTY, EMPTY,EMPTY});
            computer.getPosition(board);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("duration in milliseconds= " + duration);
        assertTrue(true);
    }
}