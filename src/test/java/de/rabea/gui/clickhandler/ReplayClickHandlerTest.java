package de.rabea.gui.clickhandler;

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
}