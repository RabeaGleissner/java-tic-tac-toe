package de.rabea.player;

import de.rabea.console.ConsoleUi;
import de.rabea.console.FakeConsole;
import de.rabea.console.FakeConsoleUserInterface;
import de.rabea.console.PrettyBoardPainter;
import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsolePlayerTest {
    private FakeConsole fakeConsole;
    private ConsolePlayer consolePlayer;

    @Before
    public void setup() {
        fakeConsole = new FakeConsole();
        consolePlayer = new ConsolePlayer(new ConsoleUi(fakeConsole, new PrettyBoardPainter()), X);
    }

    @Test
    public void convertsUserPositionIntoInteger() {
        fakeConsole.userInput("2");
        assertEquals(1, consolePlayer.getMove(new Board(3)));
    }

    @Test
    public void givesWarningWhenUserEntryIsNotANumber() {
        fakeConsole.userInput("NaN", "8");
        consolePlayer.getMove(new Board(3));
        assertEquals("Please select a position for your mark.", fakeConsole.messagePrinted());
    }

    @Test
    public void returnsPlayersMark() {
        assertEquals(X, consolePlayer.mark());
    }

    @Test
    public void asksUserAgainWhenPositionIsOccupied() {
        FakeConsoleUserInterface fakeConsoleUserInterface = new FakeConsoleUserInterface();
        ConsolePlayer consolePlayer = new ConsolePlayer(fakeConsoleUserInterface, X);
        Board board = new Board(3);
        Board nextBoard = board.placeMark(0, X);
        fakeConsoleUserInterface.choosePositions("1", "7", "3", "4", "2");
        fakeConsoleUserInterface.replayChoice("no");
        consolePlayer.getMove(nextBoard);
        assertTrue(fakeConsoleUserInterface.wasPositionUnavailableWarningCalled());
    }
}