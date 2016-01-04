package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Cell.O;
import static de.rabea.game.Cell.X;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    Game game;
    Board board;
    Rules rules;
    ComputerPlayer computerPlayer;
    RandomNumberCalc randomNumberCalc;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        randomNumberCalc = new RandomNumberCalc();
        board = new Board();
        rules = new Rules(board);
        computerPlayer = new ComputerPlayer(randomNumberCalc);
        game = new Game(fakeUserInterface, computerPlayer);
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
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "y", "1", "2", "5", "9", "7", "3", "6", "4", "8", "n");
        game.play();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameOnce() {
        FakeRandomNumberCalc fakeRandomNumberCalc = new FakeRandomNumberCalc();
        ComputerPlayer fakeComputerPlayer = new ComputerPlayer(fakeRandomNumberCalc);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, fakeComputerPlayer);
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "n");
        fakeRandomNumberCalc.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play();
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameTwice() {
        FakeRandomNumberCalc fakeRandomNumberCalc = new FakeRandomNumberCalc();
        ComputerPlayer fakeComputerPlayer = new ComputerPlayer(fakeRandomNumberCalc);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, fakeComputerPlayer);
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "y", "1", "2", "5", "8", "n");
        fakeRandomNumberCalc.giveNumbers(1, 2, 2, 3 );
//        TODO: figure out why this doesn't work'
//        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "y", "1", "3", "6", "2", "n");
//        fakeRandomNumberCalc.giveNumbers(1, 2, 0, 3, 6);
        gameWithFakeComputerPlayer.play();
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void asksUserAgainIfPositionIsInvalid() {
        board.placeMark(0, X);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.usersPosition(board);
        assertTrue(fakeUserInterface.wasPositionUnavailableWarningCalled());
    }

    @Test
    public void computerPlaysItsTurn() {
        board.placeMark(0, X);
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(4, O);
        board.placeMark(5, X);
        board.placeMark(6, O);
        board.placeMark(7, X);
        game.playOneComputerRound(board, O);
        assertFalse(fakeUserInterface.wasAskForPositionCalled());
        Cell cells[] = {X,X,O,X,O,X,O,X,O};
        assertArrayEquals(cells, board.cells());
    }
}