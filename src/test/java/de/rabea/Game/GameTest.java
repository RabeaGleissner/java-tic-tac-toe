package de.rabea.game;

import de.rabea.ui.FakeConsole;
import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

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
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
    }

    public static class FakeUserInterface extends UserInterface {

        public boolean greetUserWasCalled = false;
        public boolean askForPositionWasCalled = false;

        public FakeUserInterface() {
            super(new FakeConsole());
        }

        @Override
        public void greet() {
            greetUserWasCalled = true;
        }

        @Override
        public void askForPosition() {
            askForPositionWasCalled = true;
        }

        public boolean wasGreetUserCalled() {
            return greetUserWasCalled;
        }

        public boolean wasAskForPositionCalled() {
            return askForPositionWasCalled;
        }
    }

}