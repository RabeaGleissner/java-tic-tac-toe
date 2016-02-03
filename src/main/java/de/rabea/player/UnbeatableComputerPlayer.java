package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class UnbeatableComputerPlayer extends Player {
    private final int MAXIMUM_DEPTH = 8;

    public UnbeatableComputerPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public int getPosition(Board board) {
        return minimax(MAXIMUM_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, board, mark).getMove();
    }

    private ScoredMove minimax(int remainingDepth, int alpha, int beta, Board currentBoard, Mark currentMark) {
        ScoredMove currentBestMove = resetBestScore(currentMark);
        if (currentBoard.gameOver() || remainingDepth == 0) {
            return new ScoredMove(score(currentBoard, remainingDepth), currentBestMove.getScore());
        }
        for (int position : currentBoard.emptyCells()) {
            Board nextBoardState = currentBoard.placeMarkOnNewBoard(position, currentMark, currentBoard);
            ScoredMove score = minimax(remainingDepth - 1, alpha, beta, nextBoardState, currentMark.switchMark(currentMark));
            currentBestMove = updateScore(currentMark, currentBestMove, position, score);
            if (currentMark == mark) {
                alpha = updateAlpha(alpha, currentBestMove);
            } else {
                beta = updateBeta(beta, currentBestMove);
            }
            if (alpha >= beta) {
                break;
            }
        }
        return currentBestMove;
    }

    private ScoredMove updateScore(Mark currentMark, ScoredMove currentBestMove, int position, ScoredMove score) {
        if (score.isBetterThan(currentBestMove, currentMark)) {
            currentBestMove = new ScoredMove(score.getScore(), position);
        }
        return currentBestMove;
    }

    private int updateBeta(int beta, ScoredMove currentBestScore) {
        int score = currentBestScore.getScore();
        if (score < beta) {
            beta = score;
        }
        return beta;
    }

    private int updateAlpha(int alpha, ScoredMove currentBestScore) {
        int score = currentBestScore.getScore();
        if (score > alpha) {
            alpha = score;
        }
        return alpha;
    }

    private int score(Board board, int remainingMovesCount) {
        if (isCurrentPlayerWinner(board)) {
            return remainingMovesCount;
        }
        if (board.isDrawn()){
            return 0;
        }
        return -remainingMovesCount;
    }

    private boolean isCurrentPlayerWinner(Board board) {
        return board.winningPlayerMark() == mark;
    }

    private ScoredMove resetBestScore(Mark currentMark) {
        int bestMovePlaceholder = -1;
        if (currentMark == mark) {
            return new ScoredMove(-1000, bestMovePlaceholder);
        } else {
            return new ScoredMove(1000, bestMovePlaceholder);
        }
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

        private boolean isBetterThan(ScoredMove scoredMove, Mark currentMark) {
            return (currentMark == mark && score > scoredMove.getScore()) || (currentMark != mark && score < scoredMove.getScore());
        }
    }
}
