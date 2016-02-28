package de.rabea.player;

import de.rabea.game.GameMode;
import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlayerFactoryTest {
    private FakeUserInterface fakeUserInterface;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
    }

    @Test
    public void createsHumanPlayerForAHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.HumanVsComputer) instanceof HumanPlayer);
    }

    @Test
    public void createsComputerPlayerForAHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.HumanVsComputer) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsComputerPlayerForAComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.ComputerVsHuman) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsHumanPlayerForAComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.ComputerVsHuman) instanceof HumanPlayer);
    }
}