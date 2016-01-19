package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Game game;
    Board board;
    ComputerPlayer computerPlayer;
    RandomNumberCalculator randomNumberCalculator;
    HumanPlayer humanPlayer;
    HumanPlayer humanOpponent;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        randomNumberCalculator = new RandomNumberCalculator();
        humanPlayer = new HumanPlayer(fakeUserInterface, X);
        humanOpponent = new HumanPlayer(fakeUserInterface, O);
        board = new Board();
        computerPlayer = new ComputerPlayer(randomNumberCalculator, O);
    }

    @Test
    public void playsTheHumanGameOnce() {
        game = new Game(fakeUserInterface, humanPlayer, humanOpponent, new GameSetUp(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.play();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameOnce() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(randomNumberCalculator, O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, humanPlayer, fakeComputerPlayer, new GameSetUp(fakeUserInterface));
        fakeUserInterface.provideConsoleInput( "1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play();
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanGameTwice() {
        game = new Game(fakeUserInterface, humanPlayer, humanOpponent, new GameSetUp(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "y", "2", "2", "5", "9", "7", "3", "6", "4", "8", "1", "n");
        game.play();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameTwice() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(randomNumberCalculator, O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, humanPlayer, fakeComputerPlayer,
                new GameSetUpWithFakeComputerPlayer());
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "y", "1", "3", "6", "2", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play();
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
        assertTrue(fakeComputerPlayer.wereAllComputerPositionsUsedUp());
    }

    public class GameSetUpWithFakeComputerPlayer extends GameSetUp {
        public GameSetUpWithFakeComputerPlayer() {
            super(fakeUserInterface);
        }

        @Override
        public ComputerPlayer createNewComputerPlayer() {
            FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(randomNumberCalculator, O);
            fakeComputerPlayer.giveNumbers(0,3,6);
            return fakeComputerPlayer;
        }
    }
}

