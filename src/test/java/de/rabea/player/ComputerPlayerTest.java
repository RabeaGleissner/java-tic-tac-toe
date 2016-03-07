package de.rabea.player;

import de.rabea.game.Board;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.assertEquals;

public class ComputerPlayerTest {

    @Test
    public void returnsAFakeRandomPosition() {
        FakeRandomNumberCalculator fakeRandomNumberCalc = new FakeRandomNumberCalculator();
        ComputerPlayer computerPlayer = new ComputerPlayer(fakeRandomNumberCalc, O);
        fakeRandomNumberCalc.giveNumbers(2);
        assertEquals(2, computerPlayer.makeMove(new Board(3)));
    }

    @Test
    public void returnsTheOnlyAvailablePosition() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        Board board = new Board(X, X, O, X, O, X, O, X, EMPTY);
        assertEquals(8, computerPlayer.makeMove(board));
    }

    @Test
    public void thereIsOneFewerEmptyPositionAfterTheComputerMadeItsRandomMove() {
        Board board = new Board(3);
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        int computerPosition = computerPlayer.makeMove(board);
        Board nextBoard = board.placeMark(computerPosition, O);
        assertEquals(8, nextBoard.emptyCells().size());
    }

    @Test
    public void itReturnsThePlayersMark() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalculator(), O);
        assertEquals(O, computerPlayer.mark());
    }
}