package de.rabea.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {
    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void returnsARandomPosition() {
        FakeRandomNumberCalc fakeRandomNumberCalc = new FakeRandomNumberCalc();
        ComputerPlayer computerPlayer = new ComputerPlayer(fakeRandomNumberCalc, Mark.O);
        fakeRandomNumberCalc.giveNumbers(2);
        assertEquals(2, computerPlayer.getPosition(new Board()));
    }

    @Test
    public void returnsTheOnlyAvailablePosition() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalc(), Mark.O);
        board.placeMark(0, Mark.X);
        board.placeMark(1, Mark.X);
        board.placeMark(2, Mark.O);
        board.placeMark(3, Mark.X);
        board.placeMark(4, Mark.O);
        board.placeMark(5, Mark.X);
        board.placeMark(6, Mark.O);
        board.placeMark(7, Mark.X);
        assertEquals(8, computerPlayer.getPosition(board));
    }

    @Test
    public void thereIsOneLessEmptyPositionAfterTheComputerMadeItsRandomMove() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalc(), Mark.O);
        int computerPosition = computerPlayer.getPosition(board);
        board.placeMark(computerPosition, Mark.O);
        assertEquals(8, board.emptyCells().size());
    }

    @Test
    public void itReturnsThePlayersMark() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalc(), Mark.O);
        assertEquals(Mark.O, computerPlayer.mark());

    }
}