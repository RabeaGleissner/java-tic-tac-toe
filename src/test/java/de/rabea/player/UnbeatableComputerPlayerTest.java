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
    public void itAssignsAScoreOf10ForWinningGame() {
        board = new Board(new Mark[] {X,EMPTY,X,
                                      O,O,    O,
                                      X,X,    EMPTY});
        assertEquals(1, unbeatableComputerPlayer.score(board));
    }

    @Test
    public void itAssignsAScoreOfMinus10ForALosingGame() {
        board = new Board(new Mark[] {X,X,X,
                                      O,O,EMPTY,
                                      O,X,EMPTY});
        assertEquals(-1, unbeatableComputerPlayer.score(board));
    }

    @Test
    public void itAssignsAScoreOf0ForADrawnGame() {
        board = new Board(new Mark[] {X,O,O,
                                      O,X,X,
                                      X,X,O});
        assertEquals(0, unbeatableComputerPlayer.score(board));
    }

    @Test
    public void itReturns8AsTheOnlyAvailablePosition() {
        board = new Board(new Mark[] {X,O,X,
                                      O,X,O,
                                      O,X,EMPTY});
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itReturns4AsTheOnlyAvailablePosition() {
        board = new Board(new Mark[] {X,O,    X,
                                      O,EMPTY,O,
                                      O,X,    X});
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void returnsWinningMoveIfThereIsOne() {
        board = new Board(new Mark[] {O,    X,X,
                                      O,    X,O,
                                      EMPTY,X,EMPTY});
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itBlocksAnOpponentsWinningMove() {
        board = new Board(new Mark[] {O,EMPTY,X,
                                      X,X,    EMPTY,
                                      O,EMPTY,EMPTY});
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itCreatesATrapWhenPossible() {
        board = new Board(new Mark[] {X,O,    EMPTY,
                                      X,EMPTY,EMPTY,
                                      O,EMPTY,X});
        assertEquals(2, unbeatableComputerPlayer.getPosition(board));
        //2 or 4
    }

    @Test
    public void itBlocksAnOpponentsWinningMoveEarlyInTheGame() {
        board = new Board(new Mark[] {EMPTY,EMPTY,X,
                                      EMPTY,X,    EMPTY,
                                      EMPTY,EMPTY,O});
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itPlacesAMarkToWinInTheNextRoundI() {
        board = new Board(new Mark[] {O,    X,EMPTY,
                                      EMPTY,X,EMPTY,
                                      EMPTY,O,X});
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
        //or 3
    }

    @Test
    public void itPlacesAMarkToWinInTheNextRoundII() {
        board = new Board(new Mark[] {X,    O,X,
                                      EMPTY,O,EMPTY,
                                      EMPTY,X,EMPTY});
        assertEquals(3, unbeatableComputerPlayer.getPosition(board));
        //or 5
    }

    @Test
    public void itBlocksTheOpponentsAttemptToSetATrap() {
        board = new Board(new Mark[] {X,    EMPTY,EMPTY,
                                      EMPTY,O,    EMPTY,
                                      EMPTY,EMPTY,X});
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
        //5 or 1 or 7
    }

    @Test
    public void itBlocksAnOpponentsWinningMoveWithTwoMovesLeft() {
        board = new Board(new Mark[] {O,X,    X,
                                      X,X,    O,
                                      O,EMPTY,EMPTY});
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itBlocksAnOpponentsWinWithTwoSpacesLeft() {
        board = new Board(new Mark[] {X,     O,X,
                                      O,     O,X,
                                      EMPTY, X,EMPTY});
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }
}