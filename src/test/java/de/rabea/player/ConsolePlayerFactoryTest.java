package de.rabea.player;

import de.rabea.console.FakeConsoleUserInterface;
import de.rabea.game.GameMode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConsolePlayerFactoryTest {
    private FakeConsoleUserInterface fakeConsoleUserInterface;

    @Before
    public void setup() {
        fakeConsoleUserInterface = new FakeConsoleUserInterface();
    }

    @Test
    public void createsHumanPlayerForHumanVsComputerGame() {
        ConsolePlayerFactory playerFactory = new ConsolePlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer1(GameMode.HumanVsComputer) instanceof ConsolePlayer);
    }

    @Test
    public void createsComputerPlayerForHumanVsComputerGame() {
        ConsolePlayerFactory playerFactory = new ConsolePlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer2(GameMode.HumanVsComputer) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsComputerPlayerForComputerVsHumanGame() {
        ConsolePlayerFactory playerFactory = new ConsolePlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer1(GameMode.ComputerVsHuman) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsHumanPlayerForComputerVsHumanGame() {
        ConsolePlayerFactory playerFactory = new ConsolePlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer2(GameMode.ComputerVsHuman) instanceof ConsolePlayer);
    }

    @Test
    public void createsComputerPlayer2ForGuiHumanVsComputerGame() {
        ConsolePlayerFactory playerFactory = new ConsolePlayerFactory(fakeConsoleUserInterface);
        assertTrue(playerFactory.createPlayer2(GameMode.GuiHumanVsComputer) instanceof UnbeatableComputerPlayer);
    }
}