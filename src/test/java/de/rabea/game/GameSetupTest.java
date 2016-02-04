package de.rabea.game;

import de.rabea.player.HumanPlayer;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.FakeUserInterface;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameSetupTest {

    @Test
    public void setsUpHumanVsHumanGame() {
        FakeUserInterface fakeUserInterface = new FakeUserInterface();
        fakeUserInterface.provideConsoleInput("1", "3", "1", "7", "2", "4", "3", "n");
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        GameSetup gameSetup = new GameSetup(fakeUserInterface, playerFactory);
        gameSetup.startApplication();
        assertTrue(fakeUserInterface.greetUserWasCalled);
        assertTrue(gameSetup.getPlayer() instanceof HumanPlayer);
        assertTrue(gameSetup.getOpponent() instanceof HumanPlayer);
    }
}