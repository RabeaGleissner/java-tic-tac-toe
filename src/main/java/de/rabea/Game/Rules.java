package de.rabea.game;

public class Rules {

    private final Board board;
    private final int[][] WINNING_COMBINATIONS = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Rules(Board board) {
        this.board = board;
    }

    public boolean gameOver() {
        return board.isFull() || hasWinner();
    }

    public boolean hasWinner() {
        Cell[] gameState = gameState();
        for (int[] combo : WINNING_COMBINATIONS) {
            if (anyLineMatchesAWinningCombination(gameState, combo)) {
                return true;
            }
        }
        return false;
    }

    private boolean anyLineMatchesAWinningCombination(Cell[] gameState, int[] combo) {
        return positionIsNotEmpty(gameState, combo) &&
                sameMarkInFirstAndSecondPosition(gameState, combo) &&
                sameMarkInSecondAndThirdPosition(gameState, combo);
    }

    private boolean positionIsNotEmpty(Cell[] gameState, int[] combo) {
        return gameState[combo[0]]!= Cell.EMPTY;
    }

    private boolean sameMarkInFirstAndSecondPosition(Cell[] gameState, int[] combo) {
        return gameState[combo[0]] == gameState[combo[1]];
    }

    private boolean sameMarkInSecondAndThirdPosition(Cell[] gameState, int[] combo) {
        return gameState[combo[1]] == gameState[combo[2]];
    }

    private Cell[] gameState() {
        return board.returnCells();
    }
}
