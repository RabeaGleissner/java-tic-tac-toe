package de.rabea.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeComputerPlayer extends ComputerPlayer {

    private List<Integer> listOfNumbers;

    public FakeComputerPlayer(Mark mark) {
        super(null, mark);
        listOfNumbers = new LinkedList<>();
    }

    @Override
    public int getPosition(Board board) {
        return listOfNumbers.remove(0);
    }

    public void giveNumbers(Integer ... num) {
        listOfNumbers.addAll(Arrays.asList(num));
    }

    public boolean wereAllComputerPositionsUsedUp(){
        return listOfNumbers.size() == 0;
    }
}
