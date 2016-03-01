package de.rabea.player;

import de.rabea.console.ConsoleUi;
import de.rabea.game.Board;
import de.rabea.game.Mark;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeHumanPlayer extends HumanPlayer {
    private List<Integer> positions = new LinkedList<>();

    public FakeHumanPlayer(ConsoleUi userInterface, Mark mark) {
        super(userInterface, mark);
    }

    @Override
    public int getPosition(Board board) {
        return positions.remove(0);
    }

    public void setPositions(Integer... givenPositions) {
        positions.addAll(Arrays.asList(givenPositions));

    }
}
