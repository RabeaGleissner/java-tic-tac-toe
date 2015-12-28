package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Cell.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Game game;
    Board board;
    Rules rules;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        board = new Board();
        rules = new Rules(board);
        game = new Game(fakeUserInterface);
    }

    @Test
    public void playsTheGameOnce() {
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheGameTwice() {
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "y", "1", "2", "5", "9", "7", "3", "6", "4", "8", "n");
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
}