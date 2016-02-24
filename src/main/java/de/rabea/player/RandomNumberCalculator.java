package de.rabea.player;

import java.util.Random;

public class RandomNumberCalculator {
    public int randomNumber(int numberOfEmptyCells) {
        Random random = new Random();
        return random.nextInt(numberOfEmptyCells);
    }
}
