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
    public void playsHumanGameOnce() {
        FakeHumanPlayer fakePlayer1 = new FakeHumanPlayer(fakeConsoleUI, X);
        FakeHumanPlayer fakePlayer2= new FakeHumanPlayer(fakeConsoleUI, O);
        fakePlayer1.futureMoves(0,1,2);
        fakePlayer2.futureMoves(4,5);
        fakeConsoleUI.replayChoice("no");

        Game game = new Game(fakeConsoleUI, fakePlayer1, fakePlayer2);
        game.play(new Board(3));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }

    @Test
    public void playsHumanVsComputerGameOnce() {
        FakeHumanPlayer fakeHumanPlayer = new FakeHumanPlayer(fakeConsoleUI, X);
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeHumanPlayer.futureMoves(8,7,6);
        fakeComputerPlayer.giveNumbers(1,2);
        fakeConsoleUI.replayChoice("no");

        Game gameWithFakeComputerPlayer = new Game(fakeConsoleUI, fakeHumanPlayer,
                fakeComputerPlayer);
        gameWithFakeComputerPlayer.play(new Board(3));

        assertEquals(1, fakeConsoleUI.countAnnounceGameEndCalls);
    }
}
