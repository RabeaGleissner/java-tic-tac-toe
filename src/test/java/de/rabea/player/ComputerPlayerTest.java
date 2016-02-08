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
        board = new Board(3);
    }

    @Test
    public void returnsAFakeRandomPosition() {
        FakeRandomNumberCalculator fakeRandomNumberCalc = new FakeRandomNumberCalculator();
        ComputerPlayer computerPlayer = new ComputerPlayer(fakeRandomNumberCalc, O);
        fakeRandomNumberCalc.giveNumbers(2);
        assertEquals(2, computerPlayer.getPosition(new Board(3)));
    }

    @Test
    public void returnsTheOnlyAvailablePosition() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        board.placeMarkOnExistingBoard(0, X);
        board.placeMarkOnExistingBoard(1, X);
        board.placeMarkOnExistingBoard(2, O);
        board.placeMarkOnExistingBoard(3, X);
        board.placeMarkOnExistingBoard(4, O);
        board.placeMarkOnExistingBoard(5, X);
        board.placeMarkOnExistingBoard(6, O);
        board.placeMarkOnExistingBoard(7, X);
        assertEquals(8, computerPlayer.getPosition(board));
    }

    @Test
    public void thereIsOneFewerEmptyPositionAfterTheComputerMadeItsRandomMove() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        int computerPosition = computerPlayer.getPosition(board);
        board.placeMarkOnExistingBoard(computerPosition, O);
        assertEquals(8, board.emptyCells().size());
    }

    @Test
    public void itReturnsThePlayersMark() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        assertEquals(O, computerPlayer.mark());
    }
}