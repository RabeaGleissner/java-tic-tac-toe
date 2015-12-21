package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Cell;
import de.rabea.game.Replay;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {

    private UserInterface userInterface;
    private FakeConsole fakeConsole;

    @Before
    public void setup() {
        fakeConsole = new FakeConsole();
        userInterface = new UserInterface(fakeConsole);
    }

    @Test
    public void displaysEmptyBoard() {
        Board board = new Board();
        assertThat(userInterface.displayBoard(board.returnCells())).isEqualTo("1 2 3 \n4 5 6 \n7 8 9 \n");
    }

    @Test
    public void displaysBoardWithMarks() {
        Board board = new Board();
        board.placeMark(1, Cell.X);
        board.placeMark(3, Cell.O);
        assertThat(userInterface.displayBoard(board.returnCells())).isEqualTo("1 X 3 \nO 5 6 \n7 8 9 \n");
    }

    @Test
    public void printMessageToConsole() {
        String prompt = "message to user";
        fakeConsole.print(prompt);
        assertEquals(prompt, fakeConsole.messagePrinted());
    }

    @Test
    public void getUserInput() {
        String input = "1";
        fakeConsole.userInput(input);
        assertEquals(input, fakeConsole.readUserInput());
    }

    @Test
    public void asksUserForPosition() {
        userInterface.askForPosition();
        assertEquals("Please select a position for your mark.", fakeConsole.messagePrinted());
    }

    @Test
    public void formatUserInputForPlacingAMark() {
        assertEquals(0, (int) userInterface.formatUserChoiceForPosition("1"));
    }

    @Test(expected=NullPointerException.class)
    public void formatInvalidUserInput() {
        assertEquals(0, (int) userInterface.formatUserChoiceForPosition("x"));
    }

    @Test(expected=NullPointerException.class)
    public void formatInvalidUserInput2() {
        assertEquals(0, (int) userInterface.formatUserChoiceForPosition("$%^&*"));
    }

    @Test
    public void askUserIfTheyWantToContinue() {
        assertEquals("Do you want to play again? y/n", userInterface.playAgain());
    }

    @Test
    public void userWantsToPlayAgain() {
        assertEquals(true, userInterface.wantsToPlayAgain("y"));
    }

    @Test
    public void dealWithInvalidUserInputForReplayOption() {
        assertEquals(null, userInterface.formatUserInputForReplayOption("938"));
    }

    @Test
    public void formatValidUserInputForReplayOptionYes() {
        assertEquals(Replay.YES, userInterface.formatUserInputForReplayOption("Y"));
    }

    @Test
    public void formatValidUserInputForReplayOptionNo() {
        assertEquals(Replay.NO, userInterface.formatUserInputForReplayOption("n"));

    }
}