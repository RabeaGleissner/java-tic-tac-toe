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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameRunnerTest {
    FakeUserInterface fakeUserInterface;
    PlayerFactory playerFactory;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        playerFactory = new PlayerFactory(fakeUserInterface);
    }

    @Test
    public void createsTwoHumanPlayersWhenRequested() {
        fakeUserInterface.fakeConsoleInputForOneHvH3x3Game();
        GameRunner gameRunner = new GameRunner(fakeUserInterface, playerFactory);
        Game game = gameRunner.createGame();
        assertTrue(game.getPlayer() instanceof HumanPlayer);
        assertTrue(game.getOpponent() instanceof HumanPlayer);
    }

    @Test
    public void playsHuman3x3GameTwice() {
        fakeUserInterface.fakeConsoleInputForTwoHvH3x3Games();
        GameRunner gameRunner = new GameRunner(fakeUserInterface, playerFactory);
        gameRunner.setUpGameAndPlay();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void createsHumanAndComputerPlayer() {
        fakeUserInterface.fakeConsoleInputForOneHvC3x3Game();
        GameRunner gameRunner = new GameRunner(fakeUserInterface,
                new FakePlayerFactory(fakeUserInterface, createFakeComputerPlayerWithInput()));
        Game game =  gameRunner.createGame();
        assertTrue(game.getPlayer() instanceof HumanPlayer);
        assertTrue(game.getOpponent() instanceof FakeComputerPlayer);
    }

    private FakeComputerPlayer createFakeComputerPlayerWithInput() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeComputerPlayer.giveNumbers(6, 7);
        return fakeComputerPlayer;
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