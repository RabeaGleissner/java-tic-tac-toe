package de.rabea.gui.clickhandler;

import de.rabea.game.GameRunner;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BoardSizeClickHandlerTest {

    @Test
    public void displaysBoardOnClickAction() {
        GameRunnerSpy guiAppSpy = new GameRunnerSpy();
        BoardSizeClickHandler handler = new BoardSizeClickHandler(guiAppSpy);
        handler.action("test");

        assertTrue(guiAppSpy.prepareGameWasCalled);
    }

    private class GameRunnerSpy extends GameRunner {

        private boolean prepareGameWasCalled = false;

        public GameRunnerSpy() {
            super(null, null);
        }

        @Override
        public void createBoardAndPlay(int boardSize) {
            prepareGameWasCalled = true;
        }
    }
}