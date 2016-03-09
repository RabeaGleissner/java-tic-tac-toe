package de.rabea.player;

import de.rabea.console.ConsoleUi;
import de.rabea.game.Board;
import de.rabea.game.Mark;

import java.util.LinkedList;
import java.util.List;

public class FakeConsolePlayer extends ConsolePlayer {
    private List<Integer> positions = new LinkedList<>();

    public FakeConsolePlayer(ConsoleUi userInterface, Mark mark) {
        super(userInterface, mark);
    }

    @Override
    public int getMove(Board board) {
        return positions.remove(0);
    }

    public void willMakeMoves(FakePlayerMove... moves) {
        for (FakePlayerMove move : moves) {
            positions.add(move.ordinal());
        }
    }
}
