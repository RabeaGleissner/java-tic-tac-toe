package de.rabea.player;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeRandomNumberCalculator extends RandomNumberCalculator {

    private final List<Integer> listOfNumbers;

    public FakeRandomNumberCalculator() {
        listOfNumbers = new LinkedList<>();
    }

    @Override
    public int randomNumber(int unusedNumber) {
        return listOfNumbers.remove(0);
    }

    public void giveNumbers(Integer ... num) {
        listOfNumbers.addAll(Arrays.asList(num));
    }

}
