package de.rabea.player;

import de.rabea.console.FakeConsoleUserInterface;
import de.rabea.game.GameMode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlayerFactoryTest {
    private FakeConsoleUserInterface fakeConsoleUserInterface;

    @Before
    public void setup() {
        fakeConsoleUserInterface = new FakeConsoleUserInterface();
    }

    @Test
    public void createsHumanPlayerForHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.HumanVsComputer) instanceof HumanPlayer);
    }

    @Test
    public void createsComputerPlayerForHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.HumanVsComputer) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsComputerPlayerForComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.ComputerVsHuman) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsHumanPlayerForComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.ComputerVsHuman) instanceof HumanPlayer);
    }

    @Test
    public void createsGuiPlayerForGuiHumanVsGuiHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsGuiPlayerAsOpponentForGuiHumanVsGuiHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsCompuerOpponentForGuiHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.GuiHumanVsComputer) instanceof UnbeatableComputerPlayer);
    }
}