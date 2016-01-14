package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Game game;
    Board board;
    ComputerPlayer computerPlayer;
    RandomNumberCalc randomNumberCalc;
    HumanPlayer humanPlayer;
    HumanPlayer humanOpponent;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        randomNumberCalc = new RandomNumberCalc();
        humanPlayer = new HumanPlayer(fakeUserInterface, Mark.X);
        humanOpponent = new HumanPlayer(fakeUserInterface, Mark.O);
        board = new Board();
        computerPlayer = new ComputerPlayer(randomNumberCalc, Mark.O);
    }

    @Test
    public void playsTheHumanGameOnce() {
        game = new Game(fakeUserInterface, humanPlayer, humanOpponent, new GameSetUp(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "n");
        game.play();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanGameTwice() {
        game = new Game(fakeUserInterface, humanPlayer, computerPlayer, new GameSetUp(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "y", "2", "2", "5", "9", "7", "3", "6", "4", "8", "1", "n");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameOnce() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(randomNumberCalc, Mark.O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, humanPlayer, fakeComputerPlayer, new GameSetUp(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play();
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameTwice() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(randomNumberCalc, Mark.O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, computerPlayer, fakeComputerPlayer, new GameSetUp(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "y", "1", "3", "6", "2", "n");
        fakeComputerPlayer.giveNumbers(1, 2, 0, 3, 6);
        gameWithFakeComputerPlayer.play();
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }
}