package de.rabea.game;

public class ComputerPlayer implements Player {
    private final RandomNumberCalc randomNumberCalc;
    private Mark mark;

    public ComputerPlayer(RandomNumberCalc randomNumberCalc, Mark mark) {
        this.randomNumberCalc = randomNumberCalc;
        this.mark = mark;
    }

    public Mark mark() {
        return mark;
    }

    public int getPosition(Board board) {
        int index = randomNumberCalc.randomNumber(board.emptyCells().size());
        return board.emptyCells().get(index);
    }
}
