package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Cell;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {

    private UserInterface userInterface;
    private FakeConsole fakeConsole;
    private Board board;

    @Before
    public void setup() {
        fakeConsole = new FakeConsole();
        userInterface = new UserInterface(fakeConsole);
        board = new Board();
    }

    @Test
    public void displaysEmptyBoard() {
        Board board = new Board();
        userInterface.displayBoard(board.cells());
        assertThat(fakeConsole.messagePrinted()).isEqualTo("1 2 3 \n4 5 6 \n7 8 9 \n");
    }

    @Test
    public void displaysBoardWithMarks() {
        Board board = new Board();
        board.placeMark(1, Cell.X);
        board.placeMark(3, Cell.O);
        userInterface.displayBoard(board.cells());
        assertThat(fakeConsole.messagePrinted()).isEqualTo("1 X 3 \nO 5 6 \n7 8 9 \n");
    }

    @Test
    public void itConvertsTheUserPositionIntoAnIntegerForTheProgrammeToUse() {
        fakeConsole.userInput("2");
        assertEquals((Integer) 1, userInterface.playersChosenPosition(board));
    }

    @Test
    public void itGivesAWarningWhenUserEntryIsNotANumber() {
        fakeConsole.userInput("NaN", "8");
        userInterface.playersChosenPosition(board);
        assertEquals("Please select a position for your mark.", fakeConsole.messagePrinted());
    }

    @Test
    public void userWantsToPlayAgain() {
        fakeConsole.userInput("y");
        assertTrue(userInterface.playAgain());
    }

    @Test
    public void userDoesNotWantToPlayAgain() {
        fakeConsole.userInput("n");
        assertFalse(userInterface.playAgain());
    }

    @Test
    public void userGivesBadInputForReplayOptions() {
        fakeConsole.userInput("xyz", "n");
        userInterface.playAgain();
        assertEquals("Do you want to play again? y/n", fakeConsole.messagePrinted());
    }

    @Test
    public void greetsUserAndExplainsGame() {
        userInterface.greet();
        assertEquals("Welcome to Tic Tac Toe. The first user to play is X. The second player is O.", fakeConsole.messagePrinted());
    }

    @Test
    public void announceWinner() {
        userInterface.announceGameEnd(Cell.X, true);
        assertEquals("Game over! The winner is: X", fakeConsole.messagePrinted());
    }

    @Test
    public void announceDraw() {
        userInterface.announceGameEnd(Cell.X, false);
        assertEquals("Game over! It's a draw.", fakeConsole.messagePrinted());
    }

    @Test
    public void communicatesThatPositionIsUnavailable() {
        userInterface.positionUnavailableWarning();
        assertEquals("Sorry, this position is not available!", fakeConsole.messagePrinted());
    }
}