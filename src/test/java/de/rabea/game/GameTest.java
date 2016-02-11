package de.rabea.game;

import de.rabea.player.FakeComputerPlayer;
import de.rabea.player.HumanPlayer;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    FakeUserInterface fakeUserInterface;
    GameSetup gameSetup;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        gameSetup = new GameSetup(fakeUserInterface, new PlayerFactory(fakeUserInterface));
    }

    @Test
    public void playsHuman3x3GameOnce() {
        Game game = new Game(fakeUserInterface, new HumanPlayer(fakeUserInterface, X),
                new HumanPlayer(fakeUserInterface, O));
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        game.play(3);
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsHuman4x4GameOnce() {
        Game game = new Game(fakeUserInterface, new HumanPlayer(fakeUserInterface, X),
                new HumanPlayer(fakeUserInterface, O));
        fakeUserInterface.provideConsoleInput("1", "7", "2", "12", "3", "11", "4", "n");
        game.play(4);
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsHumanVsComputer3x3GameOnce() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, new HumanPlayer(fakeUserInterface, X),
                fakeComputerPlayer);
        fakeUserInterface.provideConsoleInput("1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        gameWithFakeComputerPlayer.play(3);
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void playsHumanVsComputer4x4GameOnce() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        Game gameWithFakeComputerPlayer = new Game(fakeUserInterface, new HumanPlayer(fakeUserInterface, X),
                fakeComputerPlayer);
        fakeUserInterface.provideConsoleInput("1", "5", "9", "13", "n");
        fakeComputerPlayer.giveNumbers(5, 6, 7);
        gameWithFakeComputerPlayer.play(4);
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }
}

