package de.rabea.player;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeRandomNumberCalculator extends RandomNumberCalculator {

    private List<Integer> listOfNumbers;

    public FakeRandomNumberCalculator() {
        listOfNumbers = new LinkedList<>();
    }

    @Override
    public int randomNumber(int unusedNumber) {
        int number = listOfNumbers.remove(0);
        return number;
    }

    public void giveNumbers(Integer ... num) {
        listOfNumbers.addAll(Arrays.asList(num));
    }

}
