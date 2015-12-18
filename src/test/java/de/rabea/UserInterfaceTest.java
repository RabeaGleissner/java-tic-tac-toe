package de.rabea;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {

    private UserInterface userInterface;

    @Before
    public void setup() {
        userInterface = new UserInterface();

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
    public void asksTheUserForAPosition() {
        assertEquals("Please select a position for your mark.", userInterface.askForPosition());
    }
}