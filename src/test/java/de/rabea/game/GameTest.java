package de.rabea.game;

import de.rabea.console.FakeConsoleUserInterface;
import de.rabea.player.FakeComputerPlayer;
import de.rabea.player.FakeConsolePlayer;
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
    public void playsHumanGameOnce() {
        FakeConsolePlayer fakePlayer1 = new FakeConsolePlayer(fakeConsoleUI, X);
        FakeConsolePlayer fakePlayer2= new FakeConsolePlayer(fakeConsoleUI, O);
        fakePlayer1.futureMoves(0,1,2);
        fakePlayer2.futureMoves(4,5);
        fakeConsoleUI.replayChoice("no");

        Game game = new Game(fakeConsoleUI, fakePlayer1, fakePlayer2);
        game.play(new Board(3));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }

    @Test
    public void playsHumanVsComputerGameOnce() {
        FakeConsolePlayer fakeConsolePlayer = new FakeConsolePlayer(fakeConsoleUI, X);
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeConsolePlayer.futureMoves(8,7,6);
        fakeComputerPlayer.giveNumbers(1,2);
        fakeConsoleUI.replayChoice("no");

        Game gameWithFakeComputerPlayer = new Game(fakeConsoleUI, fakeConsolePlayer,
                fakeComputerPlayer);
        gameWithFakeComputerPlayer.play(new Board(3));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }
}
