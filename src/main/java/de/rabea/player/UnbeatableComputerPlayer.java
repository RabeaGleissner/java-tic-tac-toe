package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class UnbeatableComputerPlayer extends Player {

    public UnbeatableComputerPlayer(Mark mark) {
        super(mark);
    }

    @Override
    public int makeMove(Board board) {
        int maximumDepth = 8;
        return minimax(maximumDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, board, mark).getMove();
    }

    @Override
    public boolean hasMove() {
        return true;
    }

    private ScoredMove minimax(int remainingDepth, int alpha, int beta, Board currentBoard, Mark currentMark) {
        ScoredMove currentBestMove = resetBestScore(currentMark);
        if (currentBoard.gameOver() || remainingDepth == 0) {
            return new ScoredMove(score(currentBoard, remainingDepth), currentBestMove.getScore());
        }
        for (int position : currentBoard.emptyCells()) {
            Board nextBoardState = currentBoard.placeMark(position, currentMark);
            ScoredMove score = minimax(remainingDepth - 1, alpha, beta, nextBoardState, currentMark.switchMark(currentMark));
            currentBestMove = updateScore(currentMark, currentBestMove, position, score);
            if (currentMark == mark) {
                alpha = Math.max(alpha, currentBestMove.getScore());
            } else {
                beta = Math.min(beta, currentBestMove.getScore());
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
        private final int score;
        private final int move;

        public ScoredMove(int score, int move) {
            this.score = score;
            this.move = move;
        }

        int getMove() {
           return move;
        }

        int getScore() {
            return score;
        }

        private boolean isBetterThan(ScoredMove scoredMove, Mark currentMark) {
            return (currentMark == mark && score > scoredMove.score) ||
                    (currentMark != mark && score < scoredMove.score);
        }
    }
}
