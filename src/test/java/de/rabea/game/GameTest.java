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
    GameManager gameManager;
    GameType gameType;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        randomNumberCalculator = new RandomNumberCalculator();
        humanPlayer = new HumanPlayer(fakeUserInterface, X);
        humanOpponent = new HumanPlayer(fakeUserInterface, O);
        board = new Board();
        computerPlayer = new ComputerPlayer(randomNumberCalculator, O);
        gameManager = new GameManager(fakeUserInterface);
        gameType = new GameType(fakeUserInterface, gameManager);
    }

    @Test
    public void playsTheHumanGameOnce() {
        game = new Game(fakeUserInterface, humanPlayer, humanOpponent, gameManager);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.play();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanVsComputerGameOnce() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, humanPlayer, fakeComputerPlayer, gameManager);
        fakeUserInterface.provideConsoleInput( "1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play();
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsTheHumanGameTwice() {
        GameManager gameManager = new GameManager(fakeUserInterface);
        game = new Game(fakeUserInterface, humanPlayer, humanOpponent, gameManager);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "y", "2", "2", "5", "9", "7", "3", "6", "4", "8", "1", "n");
        game.play();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }
}

