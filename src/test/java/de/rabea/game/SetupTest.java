package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetupTest {

    private FakeUserInterface fakeUserInterface;
    private Setup setup;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        setup = new Setup(fakeUserInterface);
    }


    @Test
    public void startsTheApplication() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "n");
        setup.startApplication();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }
}