package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

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
        game = new Game(fakeUserInterface, board, rules);
    }

    @Test
    public void greetUserOnGameStart() {
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
    }

}