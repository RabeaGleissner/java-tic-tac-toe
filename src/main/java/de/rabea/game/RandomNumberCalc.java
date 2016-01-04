package de.rabea.game;

public class RandomNumberCalc {
    public int randomPosition(int numberOfCells) {
        return (int) (Math.random() * numberOfCells);
    }
}
