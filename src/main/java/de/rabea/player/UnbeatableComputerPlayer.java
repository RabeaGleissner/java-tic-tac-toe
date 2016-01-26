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
        int remainingMovesCount = board.emptyCells().size();
        return minimax(remainingMovesCount, board, mark).getMove();
    }

    private ScoredMove minimax(int remainingMovesCount, Board currentBoard, Mark currentMark) {
        int BEST_MOVE_PLACEHOLDER = -1;

        int bestScore = resetBestScore(currentMark);
        int bestMove = BEST_MOVE_PLACEHOLDER;

        List<Integer> freePositions = currentBoard.emptyCells();
        if (currentBoard.gameOver()) {
            return new ScoredMove(score(currentBoard, remainingMovesCount), bestMove);
        }

        for (Integer position : freePositions) {
            Board nextBoardState = currentBoard.placeMarkOnNewBoard(position, currentMark, currentBoard);
            int score = minimax(remainingMovesCount - 1, nextBoardState, currentMark.switchMark(currentMark)).getScore();

            if (betterScoreForCurrentPlayer(currentMark, bestScore, score)) {
                bestScore = score;
                bestMove = position;
            }
        }
        return new ScoredMove(bestScore, bestMove);
    }

    private int score(Board board, int remainingPositions) {
        int MAXIMISING_SCORE = 100;
        int MINIMISING_SCORE = -100;

        if (board.winningPlayerMark() == mark) {
            return MAXIMISING_SCORE - remainingPositions;
        }
        if (board.isDrawn()){
            return 0;
        }
        return MINIMISING_SCORE - remainingPositions;
    }

    private boolean betterScoreForCurrentPlayer(Mark currentMark, int bestScore, int score) {
        return (currentMark == mark && score > bestScore) || (currentMark != mark && score < bestScore);
    }

    private int resetBestScore(Mark currentMark) {
        return currentMark == mark ? -1000 : 1000;
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
