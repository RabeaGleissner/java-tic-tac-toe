package de.rabea.game;

import org.junit.Test;

import static de.rabea.game.Cell.O;
import static de.rabea.game.Cell.X;
import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {

    @Test
    public void returnsARandomPosition() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new FakeRandomNumberCalc(), new Board());
        assertEquals(2, computerPlayer.getPosition());
    }

    @Test
    public void returnsTheOnlyAvailablePosition() {
        Board board = new Board();
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalc(), board);
        board.placeMark(0, X);
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(4, O);
        board.placeMark(5, X);
        board.placeMark(6, O);
        board.placeMark(7, X);
        assertEquals(8, computerPlayer.getPosition());
    }

    private class FakeRandomNumberCalc extends RandomNumberCalc {
        @Override
        public int randomNumber(int number) {
            return 2;
        }
    }
}