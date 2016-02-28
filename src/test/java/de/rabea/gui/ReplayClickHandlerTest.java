package de.rabea.gui;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ReplayClickHandlerTest {

    @Test
    public void displaysGameOptionsOnClick() {
        GuiAppSpy guiAppSpy = new GuiAppSpy();
        ReplayClickHandler handler = new ReplayClickHandler(guiAppSpy);
        handler.action("test");

        assertTrue(guiAppSpy.displayGameModeOptionsWasCalled);
    }

    private class GuiAppSpy extends GuiApp {
        private boolean displayGameModeOptionsWasCalled;

        public GuiAppSpy() {
            super(null, null);
        }

        @Override
        public void displayGameModeOptions() {
            displayGameModeOptionsWasCalled = true;
        }
    }

}