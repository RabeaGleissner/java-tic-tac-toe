package de.rabea.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeRandomNumberCalc extends RandomNumberCalc {

    private List<Integer> listOfNumbers;

    public FakeRandomNumberCalc() {
        listOfNumbers = new LinkedList<Integer>();
    }

    @Override
    public int randomNumber(int num) {
        int number = listOfNumbers.remove(0);
        return number;
    }

    public void giveNumbers(Integer ... num) {
        listOfNumbers.addAll(Arrays.asList(num));
    }

}
