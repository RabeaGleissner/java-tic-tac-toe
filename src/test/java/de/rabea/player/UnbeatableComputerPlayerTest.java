package de.rabea.player;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnbeatableComputerPlayerTest {
    private UnbeatableComputerPlayer unbeatableComputerPlayer;

    @Before
    public void setup() {
        unbeatableComputerPlayer = new UnbeatableComputerPlayer(O);
    }


    @Test
    public void createsTrapWhenPossibleOn3x3() {
        Board board = new Board(X,O, EMPTY,
                                X,EMPTY,EMPTY,
                                O,EMPTY,X);
        assertEquals(4, unbeatableComputerPlayer.getMove(board));
    }

    @Test
    public void blocksOpponentsWinningMoveOn3x3() {
        Board board = new Board(O,EMPTY,X,
                                X,X, EMPTY,
                                O,EMPTY,EMPTY);
        assertEquals(5, unbeatableComputerPlayer.getMove(board));
    }

    @Test
    public void blocksOpponentsWinningMoveEarlyOn3x3() {
        Board board = new Board(EMPTY,EMPTY,X,
                                EMPTY,X, EMPTY,
                                EMPTY,EMPTY,O);
        assertEquals(6, unbeatableComputerPlayer.getMove(board));
    }

    @Test
    public void makesWinningMoveOn4x4() {
        Board board = new Board(X, O, X, O,
                X, O, X, O,
                EMPTY, O, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(13, unbeatableComputerPlayer.getMove(board));
    }

    @Test
    public void blocksOpponentsWinningMoveOn4x4() {
        Board board = new Board(X, O, X, O,
                                X, O, X, O,
                                X, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(12, unbeatableComputerPlayer.getMove(board));
    }

    @Test
    public void blocksOpponentsWinningMoveWithAllEmptySlotsOn4x4() {
        Board board = new Board(X, X, X, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY,
                                EMPTY, EMPTY, EMPTY, EMPTY);
        assertEquals(3, unbeatableComputerPlayer.getMove(board));
    }

    @Test
    public void alwaysHasMoveAvailable() {
        assertTrue(unbeatableComputerPlayer.hasMove());
    }
}