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
        assertTrue(playerFactory.createPlayer1(GameMode.HumanVsComputer) instanceof HumanPlayer);
    }

    @Test
    public void createsComputerPlayerForHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer2(GameMode.HumanVsComputer) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsComputerPlayerForComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer1(GameMode.ComputerVsHuman) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsHumanPlayerForComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer2(GameMode.ComputerVsHuman) instanceof HumanPlayer);
    }

    @Test
    public void createsGuiPlayerForGuiHumanVsGuiHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer1(GameMode.GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsGuiPlayer2ForGuiHumanVsGuiHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer2(GameMode.GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsComputerPlayer2ForGuiHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer2(GameMode.GuiHumanVsComputer) instanceof UnbeatableComputerPlayer);
    }
}