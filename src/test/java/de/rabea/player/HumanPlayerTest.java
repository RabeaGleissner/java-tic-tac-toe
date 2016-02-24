package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.ui.*;
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
        assertEquals(1, humanPlayer.getPosition(new Board(3)));
    }

    @Test
    public void givesWarningWhenUserEntryIsNotANumber() {
        fakeConsole.userInput("NaN", "8");
        humanPlayer.getPosition(new Board(3));
        assertEquals("Please select a position for your mark.", fakeConsole.messagePrinted());
    }

    @Test
    public void returnsPlayersMark() {
        assertEquals(X, humanPlayer.mark());
    }

    @Test
    public void asksUserAgainWhenPositionIsOccupied() {
        FakeUserInterface fakeUserInterface = new FakeUserInterface();
        HumanPlayer humanPlayer = new HumanPlayer(fakeUserInterface, X);
        Board board = new Board(3);
        Board nextBoard = board.placeMark(0, X);
        fakeUserInterface.choosePositions("1", "7", "3", "4", "2");
        fakeUserInterface.replayChoice("no");
        humanPlayer.getPosition(nextBoard);
        assertTrue(fakeUserInterface.wasPositionUnavailableWarningCalled());
    }
}