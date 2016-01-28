package de.rabea.game;

import de.rabea.player.*;
import de.rabea.ui.FakeUserInterface;
import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Board board;
    ComputerPlayer computerPlayer;
    RandomNumberCalculator randomNumberCalculator;
    HumanPlayer humanPlayer;
    HumanPlayer humanOpponent;
    PlayerFactory playerFactory;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        randomNumberCalculator = new RandomNumberCalculator();
        humanPlayer = new HumanPlayer(fakeUserInterface, X);
        humanOpponent = new HumanPlayer(fakeUserInterface, O);
        board = new Board();
        playerFactory = new PlayerFactory(fakeUserInterface);
        computerPlayer = new ComputerPlayer(randomNumberCalculator, O);
    }

    @Test
    public void playsHumanGameOnce() {
        Game game = new Game(fakeUserInterface, playerFactory, humanPlayer, humanOpponent);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.play();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsHumanVsComputerGameOnce() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, playerFactory, humanPlayer, fakeComputerPlayer);
        fakeUserInterface.provideConsoleInput( "1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play();
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsHumanGameTwice() {
        Game game = new Game(fakeUserInterface, playerFactory, humanPlayer, humanOpponent);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "y", "2", "2", "5", "9", "7", "3", "6", "4", "8", "1", "n");
        game.play();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void setsUpHumanVsHumanGame() {
        Game game = new Game(fakeUserInterface, playerFactory, playerFactory.createDefaultPlayer(), playerFactory.createDefaultPlayer());
        fakeUserInterface.provideConsoleInput("1", "1", "7", "2", "4", "3", "n");
        game.setUpNewGame();
        assertTrue(game.getPlayer() instanceof HumanPlayer);
        assertTrue(game.getOpponent() instanceof HumanPlayer);
    }

    @Test
    public void startsApplication() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        FakePlayerFactory fakePlayerFactory = new FakePlayerFactory(fakeUserInterface, fakeComputerPlayer);
        Game game = new Game(fakeUserInterface, fakePlayerFactory, playerFactory.createDefaultPlayer(), playerFactory.createDefaultPlayer());
        fakeUserInterface.provideConsoleInput("2", "1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        game.startApplication();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
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

