package de.rabea.player;

import de.rabea.game.Console;
import de.rabea.game.GameMode;
import de.rabea.ui.FakeConsole;
import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.assertTrue;

public class PlayerFactoryTest {
    FakeUserInterface fakeUserInterface;
    FakeComputerPlayer fakeComputerPlayer;
    Console fakeConsole;
    PlayerFactory playerFactory;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeConsole = new FakeConsole();
        playerFactory = new PlayerFactory(fakeUserInterface);
    }

    @Test
    public void createsAHumanPlayerForAHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.HumanVsComputer) instanceof HumanPlayer);
    }

    @Test
    public void createsAComputerPlayerForAHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.HumanVsComputer) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsAComputerPlayerForAComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.ComputerVsHuman) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsAHumanPlayerForAComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.ComputerVsHuman) instanceof HumanPlayer);
    }
}