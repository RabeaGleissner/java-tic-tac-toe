package de.rabea.gui;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BoardSizeClickHandlerTest {

    @Test
    public void displaysBoardOnClickAction() {
        GuiAppSpy guiAppSpy = new GuiAppSpy();
        BoardSizeClickHandler handler = new BoardSizeClickHandler(guiAppSpy);
        handler.action("test");

        assertTrue(guiAppSpy.prepareGameWasCalled);
    }

    private class GuiAppSpy extends GuiApp {

        private boolean prepareGameWasCalled = false;

        public GuiAppSpy() {
            super(null, null);
        }

        @Override
        public void prepareGameForPlaying(String boardSize) {
            prepareGameWasCalled = true;
        }
    }
}