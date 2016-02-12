package de.rabea.player;

import de.rabea.game.Board;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertTrue;

public class ComputerPlayerSpeedTest {

    @Test(timeout=3200)
    public void measureSpeedOfFirstMoveOn3x3Board() {
        UnbeatableComputerPlayer computer = new UnbeatableComputerPlayer(X);
        Board board;
        long startTime = System.nanoTime();
        for (int i = 0; i <= 10; i++) {
            board = new Board(3);
            computer.getPosition(board);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("duration in milliseconds= " + duration);
        assertTrue(3200 > duration);
    }

    @Test(timeout=2999)
    public void measureSpeedOfFirstMoveOn4x4Board() {
        UnbeatableComputerPlayer computer = new UnbeatableComputerPlayer(X);
        Board board;
        long startTime = System.nanoTime();
        board = new Board(4);
        computer.getPosition(board);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("duration in milliseconds= " + duration);
        assertTrue(2999 > duration);
    }
}
