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


}