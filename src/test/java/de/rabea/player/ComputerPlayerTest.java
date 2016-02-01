package de.rabea.player;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {
    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    @Test
    public void returnsAFakeRandomPosition() {
        FakeRandomNumberCalculator fakeRandomNumberCalc = new FakeRandomNumberCalculator();
        ComputerPlayer computerPlayer = new ComputerPlayer(fakeRandomNumberCalc, O);
        fakeRandomNumberCalc.giveNumbers(2);
        assertEquals(2, computerPlayer.getPosition(new Board()));
    }

    @Test
    public void returnsTheOnlyAvailablePosition() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        board.placeMark(0, X);
        board.placeMark(1, X);
        board.placeMark(2, O);
        board.placeMark(3, X);
        board.placeMark(4, O);
        board.placeMark(5, X);
        board.placeMark(6, O);
        board.placeMark(7, X);
        assertEquals(8, computerPlayer.getPosition(board));
    }

    @Test
    public void thereIsOneFewerEmptyPositionAfterTheComputerMadeItsRandomMove() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        int computerPosition = computerPlayer.getPosition(board);
        board.placeMark(computerPosition, O);
        assertEquals(8, board.emptyCells().size());
    }

    @Test
    public void itReturnsThePlayersMark() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        assertEquals(O, computerPlayer.mark());
    }
}