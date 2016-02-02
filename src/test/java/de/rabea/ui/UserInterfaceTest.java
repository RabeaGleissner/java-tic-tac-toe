package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {

    private UserInterface userInterface;
    private FakeConsole fakeConsole;
    private Board board;
    private StandardBoardPainter standardBoardPainter;

    @Before
    public void setup() {
        board = new Board(3);
        fakeConsole = new FakeConsole();
        standardBoardPainter = new StandardBoardPainter();
        userInterface = new UserInterface(fakeConsole, standardBoardPainter);
    }

    @Test
    public void displaysEmptyBoard() {
        userInterface.displayBoard(board);
        assertThat(fakeConsole.messagePrinted()).isEqualTo(
                "\n" +
                "1 2 3 \n" +
                "4 5 6 \n" +
                "7 8 9 "
        );
    }


    @Test
    public void displaysBoardWithMarks() {
        board.placeMark(1, X);
        board.placeMark(3, O);
        userInterface.displayBoard(board);
        assertThat(fakeConsole.messagePrinted()).isEqualTo(
                "\n" +
                "1 X 3 \n" +
                "O 5 6 \n" +
                "7 8 9 "
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
    public void returnsHvHGameModeWhenUserEnters1() {
        fakeConsole.userInput("1");
        assertEquals(GameMode.HumanVsHuman, userInterface.getGameModeFromUser());
    }

    @Test
    public void returnsHvCGameModeWhenUserEnters2() {
        fakeConsole.userInput("2");
        assertEquals(GameMode.HumanVsComputer, userInterface.getGameModeFromUser());
    }

    @Test
    public void returnsCvHGameModeWhenUserEnters3() {
        fakeConsole.userInput("3");
        assertEquals(GameMode.ComputerVsHuman, userInterface.getGameModeFromUser());
    }
    
    @Test
    public void returnsCvCGameModeWhenUserEnters1() {
        fakeConsole.userInput("4");
        assertEquals(GameMode.ComputerVsComputer, userInterface.getGameModeFromUser());
    }

    @Test
    public void asksTheUserAgainForGameModeIfBadInputIsEntered() {
        fakeConsole.userInput("hello", "1");
        userInterface.getGameModeFromUser();
        assertEquals("Please choose the game mode. \n 1 - Human vs Human\n 2 - Human vs Computer\n 3 - Computer vs Human\n 4 - Computer vs Computer", fakeConsole.messagePrinted());
    }

    @Test
    public void tellsTheUserThatFirstPlayerIsXAndSecondIsO() {
        userInterface.announceMarkDistribution(GameMode.HumanVsHuman);
        assertEquals("The first user to play is X. The second player is O.", fakeConsole.messagePrinted());
    }

    @Test
    public void tellsTheUserThatHumanIsXAndComputerIsO() {
        userInterface.announceMarkDistribution(GameMode.HumanVsComputer);
        assertEquals("The human player is X. The computer player is O.", fakeConsole.messagePrinted());
    }

    @Test
    public void announceWinner() {
        userInterface.announceGameEnd(X, true);
        assertEquals("Game over! The winner is: X", fakeConsole.messagePrinted());
    }

    @Test
    public void announceDraw() {
        userInterface.announceGameEnd(X, false);
        assertEquals("Game over! It's a draw.", fakeConsole.messagePrinted());
    }

    @Test
    public void communicatesThatPositionIsUnavailable() {
        userInterface.positionUnavailableWarning(board);
        assertEquals("Sorry, this position is not available!", fakeConsole.messagePrinted());
    }
}