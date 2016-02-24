package de.rabea.player;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;

public class ComputerPlayerSpeedTest {

    private UnbeatableComputerPlayer computer;

    @Before
    public void setup() {
        computer = new UnbeatableComputerPlayer(X);
    }

    @Test(timeout=800)
    public void measureSpeedOfFirstMoveOn3x3Board() {
        computer.getPosition(new Board(3));
    }

    @Test(timeout=2999)
    public void measureSpeedOfFirstMoveOn4x4Board() {
        computer.getPosition(new Board(4));
    }
}
