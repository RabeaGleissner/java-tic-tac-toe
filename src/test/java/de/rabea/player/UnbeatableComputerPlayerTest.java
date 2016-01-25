package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import org.junit.Before;
import org.junit.Ignore;
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
    public void assignsAScoreOf10ForWinningGame() {
        board = new Board(new Mark[] {X,EMPTY,X,
                                      O,O,    O,
                                      X,X,    EMPTY});
        assertEquals(1, unbeatableComputerPlayer.score(board));
    }

    @Test
    public void assignsAScoreOfMinus10ForALosingGame() {
        board = new Board(new Mark[] {X,X,X,
                                      O,O,EMPTY,
                                      O,X,EMPTY});
        assertEquals(-1, unbeatableComputerPlayer.score(board));
    }

    @Test
    public void assignsAScoreOf0ForADrawnGame() {
        board = new Board(new Mark[] {X,O,O,
                                      O,X,X,
                                      X,X,O});
        assertEquals(0, unbeatableComputerPlayer.score(board));
    }

    @Test
    public void plays8AsTheOnlyAvailablePosition() {
        board = new Board(new Mark[] {X,O,X,
                                      O,X,O,
                                      O,X,EMPTY});
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void plays4AsTheOnlyAvailablePosition() {
        board = new Board(new Mark[] {X,O,    X,
                                      O,EMPTY,O,
                                      O,X,    X});
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void playsWinningMoveIfThereIsOne() {
        board = new Board(new Mark[] {O,    X,EMPTY,
                                      EMPTY,X,O,
                                      EMPTY,EMPTY,EMPTY});
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksAnOpponentsWinningMove() {
        board = new Board(new Mark[] {O,EMPTY,X,
                                      X,X,    EMPTY,
                                      O,EMPTY,EMPTY});
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void createsATrapWhenPossible() {
        board = new Board(new Mark[] {X,O,    EMPTY,
                                      X,EMPTY,EMPTY,
                                      O,EMPTY,X});
        assertTrue((2  == unbeatableComputerPlayer.getPosition(board)) ||
                4 == unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksAnOpponentsWinningMoveEarlyInTheGame() {
        board = new Board(new Mark[] {EMPTY,EMPTY,X,
                                      EMPTY,X,    EMPTY,
                                      EMPTY,EMPTY,O});
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void placesAMarkToWinInTheNextRoundI() {
        board = new Board(new Mark[] {O,    X,EMPTY,
                                      EMPTY,X,EMPTY,
                                      EMPTY,O,X});
        assertTrue((6  == unbeatableComputerPlayer.getPosition(board)) ||
                3 == unbeatableComputerPlayer.getPosition(board));
    }

    @Ignore
    @Test
    public void placesAMarkToWinInTheNextRoundII() {
        board = new Board(new Mark[] {X,    O,X,
                                      EMPTY,O,EMPTY,
                                      EMPTY,X,EMPTY});
        assertTrue((3  == unbeatableComputerPlayer.getPosition(board)) ||
                5 == unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksTheOpponentsAttemptToSetATrap() {
        board = new Board(new Mark[] {X,    EMPTY,EMPTY,
                                      EMPTY,O,    EMPTY,
                                      EMPTY,EMPTY,X});
        assertTrue((7  == unbeatableComputerPlayer.getPosition(board)) ||
                5 == unbeatableComputerPlayer.getPosition(board) ||
                7 == unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksAnOpponentsWinningMoveWithTwoMovesLeft() {
        board = new Board(new Mark[] {O,X,    X,
                                      X,X,    O,
                                      O,EMPTY,EMPTY});
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void blocksAnOpponentsWinWithTwoSpacesLeft() {
        board = new Board(new Mark[] {X,     O,X,
                                      O,     O,X,
                                      EMPTY, X,EMPTY});
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }
}