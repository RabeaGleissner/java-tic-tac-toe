package de.rabea.game;

import java.util.Random;

public class RandomNumberCalc {
    public int randomNumber(int numberOfEmptyCells) {
        Random random = new Random();
        int randomNumber = random.nextInt(numberOfEmptyCells);
        return randomNumber;
    }
}
