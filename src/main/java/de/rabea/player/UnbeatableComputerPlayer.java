package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

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
        ScoredMove currentBestScore = resetBestScore(currentMark);
        if (currentBoard.gameOver()) {
            return new ScoredMove(score(currentBoard, remainingMovesCount), currentBestScore.getScore());
        }

        for (int position : currentBoard.emptyCells()) {
            Board nextBoardState = currentBoard.placeMarkOnNewBoard(position, currentMark, currentBoard);
            ScoredMove score = minimax(remainingMovesCount - 1, nextBoardState, currentMark.switchMark(currentMark));

            if (score.isBetterThan(currentBestScore, currentMark)) {
                currentBestScore = new ScoredMove(score.getScore(), position);
            }
        }
        return currentBestScore;
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
