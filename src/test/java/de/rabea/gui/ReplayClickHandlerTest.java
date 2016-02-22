package de.rabea.gui;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplayClickHandlerTest {
    @Test
    public void displaysGameOptionsOnClick() {
        GuiAppSpy guiAppSpy = new GuiAppSpy(null);
        ReplayClickHandler handler = new ReplayClickHandler(guiAppSpy);
        handler.action("test");
        assertTrue(guiAppSpy.displayGameOptionsWasCalled);
    }

    private class GuiAppSpy extends GuiApp {

        private boolean displayGameOptionsWasCalled;

        public GuiAppSpy(ViewUpdater viewUpdater) {
            super(viewUpdater);
        }

        @Override
        public void displayGameOptions() {
            displayGameOptionsWasCalled = true;
        }
    }

}