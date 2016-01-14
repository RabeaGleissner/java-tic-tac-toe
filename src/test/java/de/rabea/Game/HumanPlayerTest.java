package de.rabea.game;

import de.rabea.ui.FakeConsole;
import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanPlayerTest {
    FakeConsole fakeConsole;
    HumanPlayer humanPlayer;
    Board board;

    @Before
    public void setup() {
        fakeConsole = new FakeConsole();
        humanPlayer = new HumanPlayer(new UserInterface(fakeConsole), Mark.X);
        board = new Board();
    }

    @Test
    public void itConvertsTheUserPositionIntoAnIntegerForTheProgrammeToUse() {
        fakeConsole.userInput("2");
        assertEquals( 1, humanPlayer.getPosition(board));
    }

    @Test
    public void itGivesAWarningWhenUserEntryIsNotANumber() {
        fakeConsole.userInput("NaN", "8");
        humanPlayer.getPosition(board);
        assertEquals("Please select a position for your mark.", fakeConsole.messagePrinted());
    }

    @Test
    public void itReturnsThePlayersMark() {
        assertEquals(Mark.X, humanPlayer.mark());

    }
}