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
        int BEST_MOVE_PLACEHOLDER = -1;

        int currentBestScore = resetBestScore(currentMark);
        int bestMove = BEST_MOVE_PLACEHOLDER;

        if (currentBoard.gameOver()) {
            return new ScoredMove(score(currentBoard, remainingMovesCount), bestMove);
        }

        for (int position : currentBoard.emptyCells()) {
            Board nextBoardState = currentBoard.placeMarkOnNewBoard(position, currentMark, currentBoard);
            ScoredMove score = minimax(remainingMovesCount - 1, nextBoardState, currentMark.switchMark(currentMark));

            if (score.isBetterThan(currentBestScore, currentMark)) {
                currentBestScore = score.getScore();
                bestMove = position;
            }
        }
        return new ScoredMove(currentBestScore, bestMove);
    }

    private int score(Board board, int remainingPositions) {
        int maximisingScore = 100;
        int minimisingScore = -100;

        if (isCurrentPlayerWinner(board)) {
            return maximisingScore - remainingPositions;
        }
        if (board.isDrawn()){
            return 0;
        }
        return minimisingScore - remainingPositions;
    }

    private boolean isCurrentPlayerWinner(Board board) {
        return board.winningPlayerMark() == mark;
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
        private boolean isBetterThan(int currentBestScore, Mark currentMark) {
            return (currentMark == mark && score > currentBestScore) || (currentMark != mark && score < currentBestScore);
        }
    }
}
