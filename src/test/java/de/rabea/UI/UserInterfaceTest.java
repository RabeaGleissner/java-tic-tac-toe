package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Cell;
import de.rabea.game.GameMode;
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
        assertThat(fakeConsole.messagePrinted()).isEqualTo(
                "\n" +
                "| 1 | 2 | 3 | \n" +
                " -----------\n" +
                "| 4 | 5 | 6 | \n" +
                " -----------\n" +
                "| 7 | 8 | 9 |\n"
        );
    }

    @Test
    public void displaysBoardWithMarks() {
        Board board = new Board();
        board.placeMark(1, Cell.X);
        board.placeMark(3, Cell.O);
        userInterface.displayBoard(board.cells());
        assertThat(fakeConsole.messagePrinted()).isEqualTo(
                "\n" +
                "| 1 | \u001B[34mX\u001B[0m | 3 | \n" +
                " -----------\n" +
                "| \u001B[31mO\u001B[0m | 5 | 6 | \n" +
                " -----------\n" +
                "| 7 | 8 | 9 |\n"
        );
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
        assertEquals("Welcome to Tic Tac Toe.\n ", fakeConsole.messagePrinted());
    }

    @Test
    public void asksWhichGameModeUserWantsToPlay() {
        fakeConsole.userInput("1");
        userInterface.chooseGameMode();
        assertEquals("Please enter 1 if you want to play against the computer and 2 if you want to play against another human player.\n", fakeConsole.messagePrinted());
    }

    @Test
    public void returnsHvCGameModeWhenUserEnters1() {
        fakeConsole.userInput("1");
        assertEquals(GameMode.HvC, userInterface.chooseGameMode());
    }

    @Test
    public void returnsHvHGameModeWhenUserEnters1() {
        fakeConsole.userInput("2");
        assertEquals(GameMode.HvH, userInterface.chooseGameMode());
    }

    @Test
    public void asksTheUserAgainForGameModeIfBadInputIsEntered() {
        fakeConsole.userInput("hello", "1");
        userInterface.chooseGameMode();
        assertEquals("Please enter 1 if you want to play against the computer and 2 if you want to play against another human player.\n", fakeConsole.messagePrinted());
    }

    @Test
    public void tellsTheUserThatFirstPlayerIsXAndSecondIsO() {
        userInterface.announceMarkDistribution(GameMode.HvH);
        assertEquals("The first user to play is X. The second player is O.", fakeConsole.messagePrinted());
    }

    @Test
    public void tellsTheUserThatHumanIsXAndComputerIsO() {
        userInterface.announceMarkDistribution(GameMode.HvC);
        assertEquals("The human player is X. The computer player is O.", fakeConsole.messagePrinted());
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