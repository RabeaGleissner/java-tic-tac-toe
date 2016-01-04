package de.rabea.game;

public class ComputerPlayer {
    private final RandomNumberCalc randomNumberCalc;

    public ComputerPlayer(RandomNumberCalc randomNumberCalc) {
        this.randomNumberCalc = randomNumberCalc;
    }

    public int getPosition(Board board) {
        int index = randomNumberCalc.randomNumber(board.cells().length);
        return board.emptyCells().get(index);
    }
}
