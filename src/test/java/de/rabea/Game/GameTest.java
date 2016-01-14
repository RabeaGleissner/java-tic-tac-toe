package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Cell.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Game game;
    Board board;
    ComputerPlayer computerPlayer;
    RandomNumberCalc randomNumberCalc;
    HumanPlayer humanPlayer;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        randomNumberCalc = new RandomNumberCalc();
        humanPlayer = new HumanPlayer(fakeUserInterface);
        board = new Board();
        computerPlayer = new ComputerPlayer(randomNumberCalc);
        game = new Game(fakeUserInterface, computerPlayer, humanPlayer);
    }

    @Test
    public void playsTheHumanGameOnce() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "n");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanGameTwice() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "y", "2", "2", "5", "9", "7", "3", "6", "4", "8", "1", "n");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameOnce() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(randomNumberCalc);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, fakeComputerPlayer, new HumanPlayer(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play();
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameTwice() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(randomNumberCalc);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, fakeComputerPlayer, new HumanPlayer(fakeUserInterface));
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "y", "1", "3", "6", "2", "n");
        fakeComputerPlayer.giveNumbers(1, 2, 0, 3, 6);
        gameWithFakeComputerPlayer.play();
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void asksUserAgainIfPositionIsInvalid() {
        board.placeMark(0, Mark.X);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.usersPosition(board);
        assertTrue(fakeUserInterface.wasPositionUnavailableWarningCalled());
    }
}