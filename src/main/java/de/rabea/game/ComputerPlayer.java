package de.rabea.game;

public class ComputerPlayer {
    private final RandomNumberCalc randomNumberCalc;
    private Board board;

    public ComputerPlayer(RandomNumberCalc randomNumberCalc, Board board) {
        this.randomNumberCalc = randomNumberCalc;
        this.board = board;
    }

    public int getPosition() {
        int index = randomNumberCalc.randomNumber(board.cells().length);
        int position = board.emptyCells().get(index);
        return position;
    }
}
