package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

import java.util.List;

public class UnbeatableComputerPlayer extends Player {


    public UnbeatableComputerPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public int getPosition(Board board) {
        int numberOfMovesLeft = board.emptyCells().size();
        return minimax(numberOfMovesLeft, board, mark).getMove();
    }

    private ScoredMove minimax(int depth, Board currentBoard, Mark currentMark) {
        int bestScore = resetBestScore(currentMark);
        int bestMove = -1;

        List<Integer> availablePositions = currentBoard.emptyCells();
        if (currentBoard.gameOver() || availablePositions.size() == 0) {
            return new ScoredMove(score(currentBoard, depth), -1);
        }

        for (Integer position : availablePositions) {
            Board currentBoardState = currentBoard.placeMarkOnNewBoard(position, currentMark, currentBoard);
            int score = minimax(depth -1, currentBoardState, currentMark.switchMark(currentMark)).getScore();

            if ((currentMark == mark && score >= bestScore) || (currentMark != mark && score <= bestScore)) {
                bestScore = score;
                bestMove = position;
            }
        }
        return new ScoredMove(bestScore, bestMove);
    }

    private int resetBestScore(Mark currentMark) {
        return currentMark == mark ? -1000 : 1000;
    }

    public int score(Board board, int depth) {
        if (board.winningPlayerMark() == mark) {
            return 100 - depth;
        }
        if (board.isDrawn()){
            return 0;
        }
        return depth - 100;
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

        public int getScore() {
            return score;
        }
    }
}
