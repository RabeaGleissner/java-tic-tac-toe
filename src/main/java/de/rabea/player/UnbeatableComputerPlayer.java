package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

import static de.rabea.game.Mark.*;

public class UnbeatableComputerPlayer implements Player {

    private final Mark computerMark = O;

    @Override
    public Mark mark() {
        return null;
    }

    @Override
    public int getPosition(Board board) {
        return 0;
    }

    public int score(Board board) {
        if (board.winningPlayerMark() == computerMark) {
            return 1;
        }
        if (board.isDrawn()){
            return 0;
        }
        return -1;
    }
}
