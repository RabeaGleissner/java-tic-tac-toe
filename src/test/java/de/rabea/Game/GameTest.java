package de.rabea.game;

import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Game game;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        game = new Game(fakeUserInterface);
    }

    @Test
    public void greetUserOnGameStart() {
        game.play();
        assertEquals(true, fakeUserInterface.wasGreetUserCalled());
    }

    public static class FakeUserInterface extends UserInterface {

        public boolean greetUserWasCalled = false;
        public FakeUserInterface() {
            super(null);
        }

        @Override
        public void greet() {
            greetUserWasCalled = true;
        }

        public boolean wasGreetUserCalled() {
            return greetUserWasCalled;
        }
    }

}