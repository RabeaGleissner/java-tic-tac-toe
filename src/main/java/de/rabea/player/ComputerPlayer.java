package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class ComputerPlayer extends Player {
    private final RandomNumberCalculator randomNumberCalculator;

    public ComputerPlayer(RandomNumberCalculator randomNumberCalculator, Mark mark) {
        super(mark);
        this.randomNumberCalculator = randomNumberCalculator;
    }

    @Override
    public int getMove(Board board) {
        int index = randomNumberCalculator.randomNumber(board.emptyCells().size());
        return board.emptyCells().get(index);
    }

    @Override
    public boolean hasMove() {
        return true;
    }
}
