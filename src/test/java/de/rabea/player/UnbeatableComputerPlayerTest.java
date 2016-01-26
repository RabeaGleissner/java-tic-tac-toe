package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.*;

public class UnbeatableComputerPlayerTest {
    UnbeatableComputerPlayer unbeatableComputerPlayer;
    Board board;

    @Before
    public void setup() {
        board = new Board(new Mark[] {EMPTY, EMPTY, EMPTY,
                                      EMPTY, EMPTY, EMPTY,
                                      EMPTY, EMPTY, EMPTY});
        unbeatableComputerPlayer = new UnbeatableComputerPlayer(O);
    }

    @Test
    public void playsOnlyAvailablePosition() {
        board = new Board(new Mark[] {X,O,X,
                                      O,X,O,
                                      O,X,EMPTY});
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void playsWinningMove() {
        board = new Board(new Mark[] {O,    X,EMPTY,
                                      EMPTY,X,O,
                                      EMPTY,EMPTY,EMPTY});
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void createsTrapWhenPossible() {
        board = new Board(new Mark[] {X,O,    EMPTY,
                                      X,EMPTY,EMPTY,
                                      O,EMPTY,X});
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void placesMarkToTryToWinInTheNextRound() {
        board = new Board(new Mark[] {X,    O,X,
                                      EMPTY,O,EMPTY,
                                      EMPTY,X,EMPTY});
        assertTrue(possibleMovesThreeAndFive(unbeatableComputerPlayer.getPosition(board)));
    }
    private boolean possibleMovesThreeAndFive(int position) {
        return position == 3 || position == 5;
    }

    @Test
    public void blocksOpponentsWinningMove() {
        board = new Board(new Mark[] {O,EMPTY,X,
                                      X,X,    EMPTY,
                                      O,EMPTY,EMPTY});
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksOpponentsWinningMoveEarlyInTheGame() {
        board = new Board(new Mark[] {EMPTY,EMPTY,X,
                                      EMPTY,X,    EMPTY,
                                      EMPTY,EMPTY,O});
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksTheOpponentsAttemptToSetATrap() {
        board = new Board(new Mark[] {X,    EMPTY,EMPTY,
                                      EMPTY,O,    EMPTY,
                                      EMPTY,EMPTY,X});
        assertTrue(doesNotHelpOpponentToSetATrap(unbeatableComputerPlayer.getPosition(board)));
    }
    private boolean doesNotHelpOpponentToSetATrap(int position) {
        return !(position == 2 || position == 6);
    }

    @Test
    public void blocksOpponentsWinningMoveWithTwoMovesLeft() {
        board = new Board(new Mark[] {O,X,    X,
                                      X,X,    O,
                                      O,EMPTY,EMPTY});
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksAnOpponentsWinWithFourMovesLeft() {
        board = new Board(new Mark[] {X,     EMPTY,X,
                                      O,     EMPTY,X,
                                      EMPTY, EMPTY,O});
        assertEquals(1, unbeatableComputerPlayer.getPosition(board));
    }
}