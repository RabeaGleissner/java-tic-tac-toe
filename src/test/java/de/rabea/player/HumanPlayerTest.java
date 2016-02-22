package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.ui.*;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanPlayerTest {
    FakeConsole fakeConsole;
    HumanPlayer humanPlayer;
    Board board;

    @Before
    public void setup() {
        fakeConsole = new FakeConsole();
        humanPlayer = new HumanPlayer(new ConsoleUi(fakeConsole, new PrettyBoardPainter()), X);
        board = new Board(3);
    }

    @Test
    public void convertsUserPositionIntoInteger() {
        fakeConsole.userInput("2");
        assertEquals(1, humanPlayer.getPosition(board));
    }

    @Test
    public void givesWarningWhenUserEntryIsNotANumber() {
        fakeConsole.userInput("NaN", "8");
        humanPlayer.getPosition(board);
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
        board.placeMarkOnExistingBoard(0, X);
        fakeUserInterface.provideConsoleInput("1", "7", "3", "4", "2", "n");
        humanPlayer.getPosition(board);
        assertTrue(fakeUserInterface.wasPositionUnavailableWarningCalled());
    }
}