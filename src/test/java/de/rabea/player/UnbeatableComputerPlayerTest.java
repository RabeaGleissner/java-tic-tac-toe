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
        unbeatableComputerPlayer = new UnbeatableComputerPlayer();
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
        board.placeMark(0, X);
        board.placeMark(1, O);
        board.placeMark(2, X);
        board.placeMark(3, O);
        board.placeMark(4, X);
        board.placeMark(5, O);
        board.placeMark(6, O);
        board.placeMark(7, X);
        assertEquals(8, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itReturns4AsTheOnlyAvailablePosition() {
        board.placeMark(0, X);
        board.placeMark(1, O);
        board.placeMark(2, X);
        board.placeMark(3, O);
        board.placeMark(8, X);
        board.placeMark(5, O);
        board.placeMark(6, O);
        board.placeMark(7, X);
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void returnsWinningMoveIfThereIsOne() {
        board.placeMark(1,X);
        board.placeMark(0,O);
        board.placeMark(2,X);
        board.placeMark(3,O);
        board.placeMark(4,X);
        board.placeMark(5,O);
        board.placeMark(7,X);
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itBlocksAnOpponentsWinningMove() {
        board.placeMark(2, X);
        board.placeMark(0, O);
        board.placeMark(3, X);
        board.placeMark(6, O);
        board.placeMark(4, X);
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itCreatesATrapWhenPossible() {
        board.placeMark(0, X);
        board.placeMark(1, O);
        board.placeMark(3, X);
        board.placeMark(6, O);
        board.placeMark(8, X);
        assertEquals(4, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itBlocksAnOpponentsWinningMoveEarlyInTheGame() {
        board.placeMark(4, X);
        board.placeMark(8, O);
        board.placeMark(2, X);
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itPlacesAMarkToWinInTheNextRound() {
        board.placeMark(4, X);
        board.placeMark(0, O);
        board.placeMark(1, X);
        board.placeMark(7, O);
        board.placeMark(8, X);
        assertEquals(6, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itBlocksTheOpponentsAttemptToSetATrap() {
        board.placeMark(0, X);
        board.placeMark(4, O);
        board.placeMark(8, X);
        assertEquals(5, unbeatableComputerPlayer.getPosition(board));
    }

    @Test
    public void itBlocksAnOpponentsWinningMoveWithTwoMovesLeft() {
        board.placeMark(0, O);
        board.placeMark(1, X);
        board.placeMark(2, X);
        board.placeMark(3, X);
        board.placeMark(4, X);
        board.placeMark(5, O);
        board.placeMark(6, O);
        assertEquals(7, unbeatableComputerPlayer.getPosition(board));
    }
}