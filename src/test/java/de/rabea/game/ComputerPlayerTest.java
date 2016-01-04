package de.rabea.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
    @Test
    public void returnsARandomPosition() {
        ComputerPlayer computerPlayer = new ComputerPlayer(new FakeRandomNumberCalc(), new Board());
        assertEquals(2, computerPlayer.getPosition());
    }

    private class FakeRandomNumberCalc extends RandomNumberCalc {
        @Override
        public int randomPosition(int numberOfCells) {
            return 2;
        }
    }
}