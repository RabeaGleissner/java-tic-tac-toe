package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeComputerPlayer extends ComputerPlayer {

    private final List<Integer> positions;

    public FakeComputerPlayer(Mark mark) {
        super(null, mark);
        positions = new LinkedList<>();
    }

    @Override
    public int getMove(Board board) {
        return positions.remove(0);
    }

    public void willMakeMoves(FakePlayerMove... moves) {
        for (FakePlayerMove move : moves) {
            positions.add(move.ordinal());
        }
        positions.addAll(Arrays.asList());
    }
}
