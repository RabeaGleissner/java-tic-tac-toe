package de.rabea.game;

import de.rabea.console.FakeConsoleUserInterface;
import de.rabea.player.FakeComputerPlayer;
import de.rabea.player.FakeHumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;

public class GameTest {
    private FakeConsoleUserInterface fakeConsoleUI;

    @Before
    public void setup() {
        fakeConsoleUI = new FakeConsoleUserInterface();
    }

    @Test
    public void playsHuman3x3GameOnce() {
        FakeHumanPlayer fakePlayer = new FakeHumanPlayer(fakeConsoleUI, X);
        FakeHumanPlayer fakeOpponent = new FakeHumanPlayer(fakeConsoleUI, O);
        fakePlayer.setPositions(0,1,2);
        fakeOpponent.setPositions(4,5);
        fakeConsoleUI.replayChoice("no");

        Game game = new Game(fakeConsoleUI, fakePlayer, fakeOpponent);
        game.play(new Board(3));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }

    @Test
    public void playsHuman4x4GameOnce() {
        FakeHumanPlayer fakePlayer = new FakeHumanPlayer(fakeConsoleUI, X);
        FakeHumanPlayer fakeOpponent = new FakeHumanPlayer(fakeConsoleUI, O);
        fakePlayer.setPositions(0,1,2,3);
        fakeOpponent.setPositions(10,11,12);
        fakeConsoleUI.replayChoice("no");

        Game game = new Game(fakeConsoleUI, fakePlayer, fakeOpponent);
        game.play(new Board(4));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }

    @Test
    public void playsHumanVsComputer3x3GameOnce() {
        FakeHumanPlayer fakeHumanPlayer = new FakeHumanPlayer(fakeConsoleUI, X);
        FakeComputerPlayer fakeComputerOpponent = new FakeComputerPlayer(O);
        fakeHumanPlayer.setPositions(8,7,6);
        fakeComputerOpponent.giveNumbers(1,2);
        fakeConsoleUI.replayChoice("no");

        Game gameWithFakeComputerPlayer = new Game(fakeConsoleUI, fakeHumanPlayer,
                fakeComputerOpponent);
        gameWithFakeComputerPlayer.play(new Board(3));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }

    @Test
    public void playsHumanVsComputer4x4GameOnce() {
        FakeHumanPlayer fakeHumanPlayer = new FakeHumanPlayer(fakeConsoleUI, X);
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeHumanPlayer.setPositions(0,4,8,12);
        fakeComputerPlayer.giveNumbers(5,6,7);
        fakeConsoleUI.replayChoice("no");

        Game gameWithFakeComputerPlayer = new Game(fakeConsoleUI, fakeHumanPlayer,
                fakeComputerPlayer);
        gameWithFakeComputerPlayer.play(new Board(4));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }
}
