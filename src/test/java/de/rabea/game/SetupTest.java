package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SetupTest {

    private FakeUserInterface fakeUserInterface;
    private Setup setup;
    private PlayerFactory playerFactory;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        playerFactory = new PlayerFactory(fakeUserInterface);
        setup = new Setup(fakeUserInterface, playerFactory);
    }


    @Test
    public void startsTheApplication() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "n");
        setup.startApplication();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }
}