package de.rabea.game;

public class ComputerPlayer implements Player {
    private final RandomNumberCalculator randomNumberCalculator;
    private Mark mark;

    public ComputerPlayer(RandomNumberCalculator randomNumberCalculator, Mark mark) {
        this.randomNumberCalculator = randomNumberCalculator;
        this.mark = mark;
    }

    public Mark mark() {
        return mark;
    }

    public int getPosition(Board board) {
        int index = randomNumberCalculator.randomNumber(board.emptyCells().size());
        return board.emptyCells().get(index);
    }
}
