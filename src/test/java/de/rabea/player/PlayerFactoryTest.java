package de.rabea.player;

import de.rabea.game.GameMode;
import de.rabea.gui.GuiPlayer;
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
    public void createsHumanPlayerForHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.HumanVsComputer) instanceof HumanPlayer);
    }

    @Test
    public void createsComputerPlayerForHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.HumanVsComputer) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsComputerPlayerForComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.ComputerVsHuman) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsHumanPlayerForComputerVsHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.ComputerVsHuman) instanceof HumanPlayer);
    }

    @Test
    public void createsGuiPlayerForGuiHumanVsGuiHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createPlayer(GameMode.GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsGuiPlayerAsOpponentForGuiHumanVsGuiHumanGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsCompuerOpponentForGuiHumanVsComputerGame() {
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        assertTrue(playerFactory.createOpponent(GameMode.GuiHumanVsComputer) instanceof UnbeatableComputerPlayer);
    }
}