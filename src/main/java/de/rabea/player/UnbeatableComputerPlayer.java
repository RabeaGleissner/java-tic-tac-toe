package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

import java.util.List;

import static de.rabea.game.Mark.*;

public class UnbeatableComputerPlayer implements Player {

    private final Mark computerMark = O;

    @Override
    public Mark mark() {
        return null;
    }

    @Override
    public int getPosition(Board board) {

        Board boardCopy = board.copy();
        return minimax(boardCopy, computerMark);
    }

    private int minimax(Board boardCopy, Mark mark) {
        List<Integer> availablePositons = boardCopy.emptyCells();
        ScoredMove bestSoFar = new ScoredMove(-2, -2);

        for (Integer position : availablePositons) {
            boardCopy.placeMark(position, mark);

            if (boardCopy.gameOver()) {
                int score = score(boardCopy);
                if (score > bestSoFar.getScore()) {
                    bestSoFar.setScore(score);
                    bestSoFar.setMove(position);
                }
            } else {
                return minimax(boardCopy, mark.switchMark(mark));
            }
        }
        return bestSoFar.getMove();
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

    public class ScoredMove {
        private int score;
        private int move;

        public ScoredMove(int score, int move) {
            this.score = score;
            this.move = move;
        }

        public int getMove() {
           return move;
        }

        public void setMove(int move) {
           this.move = move;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
           this.score = score;
        }
    }
}
