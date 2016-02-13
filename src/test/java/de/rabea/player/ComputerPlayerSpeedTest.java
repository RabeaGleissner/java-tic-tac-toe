package de.rabea.player;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertTrue;

public class ComputerPlayerSpeedTest {

    UnbeatableComputerPlayer computer;

    @Before
    public void setup() {
        computer = new UnbeatableComputerPlayer(X);
    }

    @Test(timeout=800)
    public void measureSpeedOfFirstMoveOn3x3Board() {
        assertTrue(800 > durationOfFirstComputerMove(new Board(3)));
    }

    @Test(timeout=2999)
    public void measureSpeedOfFirstMoveOn4x4Board() {
        assertTrue(2999 > durationOfFirstComputerMove(new Board(4)));
    }

    private long durationOfFirstComputerMove(Board board) {
        long startTime = System.nanoTime();
        computer.getPosition(board);
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000;
    }
}
