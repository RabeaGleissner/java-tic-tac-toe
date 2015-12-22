package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Cell;
import de.rabea.game.Replay;
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
        userInterface.displayBoard(board.returnCells());
        assertThat(fakeConsole.messagePrinted()).isEqualTo("1 2 3 \n4 5 6 \n7 8 9 \n");
    }

    @Test
    public void displaysBoardWithMarks() {
        Board board = new Board();
        board.placeMark(1, Cell.X);
        board.placeMark(3, Cell.O);
        userInterface.displayBoard(board.returnCells());
        assertThat(fakeConsole.messagePrinted()).isEqualTo("1 X 3 \nO 5 6 \n7 8 9 \n");
    }

    @Test
    public void itReturnsThePositionThatTheUserEnters() {
        fakeConsole.userInput("2");
        assertEquals((Integer) 2, userInterface.returnPlayersChosenPosition(board));
    }

    @Test
    public void itGivesAWarningWhenUserEntryIsNotANumber() {
        fakeConsole.userInput("NaN", "8");
        userInterface.returnPlayersChosenPosition(board);
        assertEquals("Please select a position for your mark.", fakeConsole.messagePrinted());
    }

    @Test
    public void asksIfUserWantsToPlayAgain() {
        fakeConsole.userInput("y");
        assertEquals(true, userInterface.playAgain());
    }

    @Test
    public void askUserIfTheyWantToPlayAgain() {
        userInterface.askForReplay();
        assertEquals("Do you want to play again? y/n", fakeConsole.messagePrinted());
    }

    @Test
    public void knowsIfUserInputIsANumber() {
        assertTrue(userInterface.isInteger("938"));
    }

    @Test
    public void knowsThatUserInputIsNotANumber() {
        assertFalse(userInterface.isInteger("asdgsadg"));
    }

    @Test
    public void formatValidUserInputForReplayOptionYes() {
        assertEquals(Replay.YES, userInterface.formatUserInputForReplayOption("Y"));
    }

    @Test
    public void formatValidUserInputForReplayOptionNo() {
        assertEquals(Replay.NO, userInterface.formatUserInputForReplayOption("n"));
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

}