package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Cell.O;
import static de.rabea.game.Cell.X;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Game game;
    Board board;
    Rules rules;
    ComputerPlayer computerPlayer;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        board = new Board();
        rules = new Rules(board);
        game = new Game(fakeUserInterface);
        computerPlayer = new ComputerPlayer(new RandomNumberCalc(), board);
    }

    @Test
    public void playsTheGameOnce() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "n");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheGameTwice() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "y", "1", "2", "5", "9", "7", "3", "6", "4", "8", "n");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void asksUserAgainIfPositionIsInvalid() {
        board.placeMark(0, X);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.usersPosition(board);
        assertTrue(fakeUserInterface.wasPositionUnavailableWarningCalled());

    }

    @Test
    public void computerPlaysItsTurn() {
        board.placeMark(0, X);
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(4, O);
        board.placeMark(5, X);
        board.placeMark(6, O);
        board.placeMark(7, X);
        game.playOneComputerRound(board, O, rules);
        assertFalse(fakeUserInterface.wasAskForPositionCalled());
        Cell cells[] = {X,X,O,X,O,X,O,X,O};
        assertArrayEquals(cells, board.cells());
    }
}