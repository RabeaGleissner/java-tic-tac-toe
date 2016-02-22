package de.rabea.gui;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardSizeClickHandlerTest {

    @Test
    public void displaysBoardOnClickAction() {
        GuiAppSpy guiAppSpy = new GuiAppSpy(null);
        BoardSizeClickHandler handler = new BoardSizeClickHandler(guiAppSpy);
        handler.action("test");
        assertTrue(guiAppSpy.displayBoardWasCalled);
    }

    private class GuiAppSpy extends GuiApp {

        private boolean displayBoardWasCalled = false;

        public GuiAppSpy(ViewUpdater viewUpdater) {
            super(viewUpdater);
        }

        @Override
        public void displayBoard() {
            displayBoardWasCalled = true;
        }
    }
}