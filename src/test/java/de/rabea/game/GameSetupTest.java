package de.rabea.game;

import de.rabea.player.FakeComputerPlayer;
import de.rabea.player.HumanPlayer;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.FakeUserInterface;
import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertTrue;

public class GameSetupTest {
    FakeUserInterface fakeUserInterface;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
    }

    @Test
    public void setsUpFirstHumanVsHuman3x3Game() {
        fakeUserInterface.provideConsoleInput("1", "3", "1", "7", "2", "4", "3", "n");
        PlayerFactory playerFactory = new PlayerFactory(fakeUserInterface);
        GameSetup gameSetup = new GameSetup(fakeUserInterface, playerFactory);
        gameSetup.startApplication();
        assertTrue(fakeUserInterface.greetUserWasCalled);
        assertTrue(gameSetup.getPlayer() instanceof HumanPlayer);
        assertTrue(gameSetup.getOpponent() instanceof HumanPlayer);
    }

    @Test
    public void setsUpFirstHumanVsComputer3x3Game() {
        fakeUserInterface.provideConsoleInput("2", "3", "1", "2", "3", "n");
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeComputerPlayer.giveNumbers(6, 7);
        FakePlayerFactory fakePlayerFactory= new FakePlayerFactory(fakeUserInterface, fakeComputerPlayer);
        GameSetup gameSetup = new GameSetup(fakeUserInterface, fakePlayerFactory);
        gameSetup.startApplication();
        assertTrue(fakeUserInterface.greetUserWasCalled);
        assertTrue(gameSetup.getPlayer() instanceof HumanPlayer);
        assertTrue(gameSetup.getOpponent() instanceof FakeComputerPlayer);
    }

    private class FakePlayerFactory extends PlayerFactory {
        private FakeComputerPlayer fakeComputerPlayer;

        public FakePlayerFactory(UserInterface userInterface, FakeComputerPlayer fakeComputerPlayer) {
            super(userInterface);
            this.fakeComputerPlayer = fakeComputerPlayer;
        }

        @Override
        public Player createPlayer(GameMode gameMode) {
            return new HumanPlayer(fakeUserInterface, X);
        }

        @Override
        public Player createOpponent(GameMode gameMode) {
            return fakeComputerPlayer;
        }
    }
}