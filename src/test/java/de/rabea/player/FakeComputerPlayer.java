package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeComputerPlayer extends ComputerPlayer {

    private final List<Integer> listOfNumbers;

    public FakeComputerPlayer(Mark mark) {
        super(null, mark);
        listOfNumbers = new LinkedList<>();
    }

    @Override
    public int getMove(Board board) {
        return listOfNumbers.remove(0);
    }

    public void giveNumbers(Integer ... num) {
        listOfNumbers.addAll(Arrays.asList(num));
    }
}
