package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerTest {

    private FakeUserInterface fakeUserInterface;
    private GameManager gameManager;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        gameManager = new GameManager(fakeUserInterface);
    }


    @Test
    public void startsTheApplication() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "n");
        gameManager.startApplication();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }
}