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

public class HumanPlayerTest {
    private FakeConsole fakeConsole;
    private HumanPlayer humanPlayer;

    @Before
    public void setup() {
        fakeConsole = new FakeConsole();
        humanPlayer = new HumanPlayer(new ConsoleUi(fakeConsole, new PrettyBoardPainter()), X);
    }

    @Test
    public void convertsUserPositionIntoInteger() {
        fakeConsole.userInput("2");
        assertEquals(1, humanPlayer.makeMove(new Board(3)));
    }

    @Test
    public void givesWarningWhenUserEntryIsNotANumber() {
        fakeConsole.userInput("NaN", "8");
        humanPlayer.makeMove(new Board(3));
        assertEquals("Please select a position for your mark.", fakeConsole.messagePrinted());
    }

    @Test
    public void returnsPlayersMark() {
        assertEquals(X, humanPlayer.mark());
    }

    @Test
    public void asksUserAgainWhenPositionIsOccupied() {
        FakeConsoleUserInterface fakeConsoleUserInterface = new FakeConsoleUserInterface();
        HumanPlayer humanPlayer = new HumanPlayer(fakeConsoleUserInterface, X);
        Board board = new Board(3);
        Board nextBoard = board.placeMark(0, X);
        fakeConsoleUserInterface.choosePositions("1", "7", "3", "4", "2");
        fakeConsoleUserInterface.replayChoice("no");
        humanPlayer.makeMove(nextBoard);
        assertTrue(fakeConsoleUserInterface.wasPositionUnavailableWarningCalled());
    }
}