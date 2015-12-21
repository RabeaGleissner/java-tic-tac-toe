package de.rabea.game;

public class Rules {

    private final Board board;
    private final int[][] WINNING_COMBINATIONS = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Rules(Board board) {
        this.board = board;

    }

    public boolean gameOver() {
        return board.isFull();
    }

    public boolean winner() {
        Cell[] gameState = gameState();
        for (int[] combos : WINNING_COMBINATIONS) {
            if (gameState[combos[0]] == gameState[combos[1]] &&
                    gameState[combos[1]] == gameState[combos[2]] &&
                    gameState[combos[2]] != Cell.EMPTY) {
                return true;
            }
        }
        return false;
    }

    private Cell[] gameState() {
        return board.returnCells();
    }
}
