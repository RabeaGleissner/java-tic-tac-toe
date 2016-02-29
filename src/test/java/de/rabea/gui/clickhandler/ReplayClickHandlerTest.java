package de.rabea.gui.clickhandler;

import de.rabea.game.GameRunner;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ReplayClickHandlerTest {

    @Test
    public void displaysGameOptionsOnClick() {
        GameRunnerSpy guiAppSpy = new GameRunnerSpy();
        ReplayClickHandler handler = new ReplayClickHandler(guiAppSpy);
        handler.action("test");

        assertTrue(guiAppSpy.displayGameModeOptionsWasCalled);
    }

    private class GameRunnerSpy extends GameRunner {
        private boolean displayGameModeOptionsWasCalled;

        public GameRunnerSpy() {
            super(null, null);
        }

        @Override
        public void displayGameModeOptions() {
            displayGameModeOptionsWasCalled = true;
        }
    }

}