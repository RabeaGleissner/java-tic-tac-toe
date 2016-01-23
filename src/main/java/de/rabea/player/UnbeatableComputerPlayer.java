package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

import java.util.List;

public class UnbeatableComputerPlayer implements Player {

    private final Mark computerMark;
    private int scoringCount = 0;
    private int depth = 0;

    public UnbeatableComputerPlayer(Mark computerMark) {
        this.computerMark = computerMark;
    }

    @Override
    public Mark mark() {
        return computerMark;
    }

    @Override
    public int getPosition(Board board) {
        depth = board.emptyCells().size();
        return minimax(depth, board, computerMark).getMove();
    }

    private ScoredMove minimax(int depth, Board currentBoard, Mark currentMark) {
        int bestScore = -10;
        int bestMove = -1;

        List<Integer> availablePositions = currentBoard.emptyCells();
        if (currentBoard.gameOver() || depth == 0 || availablePositions.size() == 0) {
            return new ScoredMove(score(currentBoard), -1);
        }

        for (Integer position : availablePositions) {
            Board currentBoardState = currentBoard.placeMarkOnNewBoard(position, currentMark, currentBoard);
            int score = minimax(depth -1, currentBoardState, currentMark.switchMark(currentMark)).getScore();

            if (currentMark == computerMark && score >= bestScore || currentMark != computerMark && score <= bestScore) {
                bestScore = score;
                bestMove = position;
            }
        }
        return new ScoredMove(bestScore, bestMove);
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

    private class ScoredMove {
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
