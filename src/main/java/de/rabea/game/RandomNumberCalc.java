package de.rabea.game;

public class RandomNumberCalc {
    public int randomNumber(int numberOfEmptyCells) {
        return (int) Math.random() * numberOfEmptyCells;
    }
}
