package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

import java.util.List;

public class UnbeatableComputerPlayer implements Player {

    private final Mark computerMark;
    private int scoringCount = 0;

    public UnbeatableComputerPlayer(Mark computerMark) {
        this.computerMark = computerMark;
    }

    @Override
    public Mark mark() {
        return computerMark;
    }

    @Override
    public int getPosition(Board board) {
        Board boardCopy = board.copy();
        return minimax(boardCopy, computerMark, board).getMove();
    }

    private ScoredMove minimax(Board boardCopy, Mark currentMark, Board board) {

        ScoredMove bestSoFar = resetScoredMove(currentMark);
        List<Integer> availablePositions = boardCopy.emptyCells();

        for (Integer position : availablePositions) {
            boardCopy.placeMark(position, currentMark);
            if (boardCopy.gameOver()) {
                int score = score(boardCopy);
                updateScoreIfNewScoreIsBetter(currentMark, bestSoFar, position, score, board);
            } else {
                Board secondBoardCopy = boardCopy.copy();
                return minimax(secondBoardCopy, currentMark.switchMark(currentMark), board);
            }
        }
        return bestSoFar;
    }

    private void updateScoreIfNewScoreIsBetter(Mark currentMark, ScoredMove bestSoFar, Integer position, int score, Board board) {
        System.out.println("scoringCount" + scoringCount);
       List<Integer> positions = board.emptyCells();
        System.out.println("positions = " + positions.toString());
        if (currentMark == computerMark && score > bestSoFar.getScore()) {
            bestSoFar.setScore(score);
            bestSoFar.setMove(positions.get(scoringCount));
        }
        if (currentMark != computerMark && score < bestSoFar.getScore()) {
            bestSoFar.setScore(score);
            bestSoFar.setMove(positions.get(scoringCount));
        }
        scoringCount++;
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

    private ScoredMove resetScoredMove(Mark currentMark) {
        if (currentMark == computerMark) {
            return new ScoredMove(-100, -100);
        } else {
            return new ScoredMove(100, 100);
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
//   Following pseudo-code in this doc: http://web.eecs.umich.edu/~akamil/teaching/sp03/minimax.pdf
//    private ScoredMove minimax(Board boardCopy, Mark currentMark) {
//        List<Integer> availablePositions = boardCopy.emptyCells();
//        ScoredMove bestSoFar = resetScoredMove(currentMark);
//        ScoredMove result;
//
//        if (boardCopy.gameOver()) {
//            int score = score(boardCopy);
//            bestSoFar = new ScoredMove(-100, score);
//        }
//
//
//        for (Integer position : availablePositions) {
//
//            boardCopy.placeMark(position, currentMark);
//            result = minimax(boardCopy, currentMark.switchMark(currentMark));
//
//            if (currentMark == computerMark && result.getScore() > bestSoFar.getScore()) {
//                bestSoFar.setScore(result.getScore());
//                bestSoFar.setMove(result.getMove());
//                //problem: position is not the first played move but one further down the line
//            }
//
//            if (currentMark != computerMark && result.getScore() < bestSoFar.getScore()) {
//                bestSoFar.setScore(result.getScore());
//                bestSoFar.setMove(result.getMove());
//            }
//        }
//        return bestSoFar;
//    }
