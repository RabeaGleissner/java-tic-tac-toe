package de.rabea.player;

import de.rabea.game.GameMode;
import org.junit.Test;

import static de.rabea.game.GameMode.GuiHumanVsGuiHuman;
import static org.junit.Assert.assertTrue;

public class GuiPlayerFactoryTest {
    @Test
    public void createsGuiPlayerForGuiHumanVsGuiHumanGame() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();
        assertTrue(playerFactory.createPlayer1(GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsGuiPlayer2ForGuiHumanVsGuiHumanGame() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();
        assertTrue(playerFactory.createPlayer2(GuiHumanVsGuiHuman) instanceof GuiPlayer);
    }

    @Test
    public void createsComputerPlayerForHumanVsComputerGame() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();
        assertTrue(playerFactory.createPlayer2(GameMode.GuiHumanVsComputer) instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void createsComputerPlayerForComputerVsHumanGame() {
        GuiPlayerFactory playerFactory = new GuiPlayerFactory();
        assertTrue(playerFactory.createPlayer1(GameMode.ComputerVsHuman) instanceof UnbeatableComputerPlayer);
    }
}