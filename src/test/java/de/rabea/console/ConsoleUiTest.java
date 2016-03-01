package de.rabea.console;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;
import de.rabea.player.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ConsoleUiTest {

    private ConsoleUi userInterface;
    private FakeConsole fakeConsole;
    private Board board;

    @Before
    public void setup() {
        board = new Board(3);
        fakeConsole = new FakeConsole();
        FakeBoardPainter fakeBoardPainter = new FakeBoardPainter();
        userInterface = new ConsoleUi(fakeConsole, fakeBoardPainter);
    }

    @Test
    public void displaysBoard() {
        Board board = new Board(3);
        userInterface.displayBoard(board, new HumanPlayer(userInterface, Mark.X));
        assertEquals(fakeConsole.messagePrinted(), "board placeholder");
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
    public void getsBoardSizeSelectionFromUser3x3() {
        fakeConsole.userInput("3");
        assertEquals(3, userInterface.getBoardDimensionFromUser());
    }

    @Test
    public void getsBoardSizeSelectionFromUser4x4() {
        fakeConsole.userInput("4");
        assertEquals(4, userInterface.getBoardDimensionFromUser());
    }

    @Test
    public void asksUserAgainForBoardSizeIfUserGivesBadInput() {
        fakeConsole.userInput("something wrong", "3");
        userInterface.getBoardDimensionFromUser();
        assertEquals("Choose a board size: \n" +
                " 3 - 3x3 board \n" +
                " 4 - 4x4 board", fakeConsole.messagePrinted());
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

    private class FakeBoardPainter extends PrettyBoardPainter {
        @Override
        public String drawBoard(Board board) {
            return "board placeholder";
        }
    }
}