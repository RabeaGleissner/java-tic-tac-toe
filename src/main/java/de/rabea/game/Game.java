package de.rabea.game;

public class Game {

    private final UserInterface userInterface;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(UserInterface userInterface, Player player1, Player player2) {
        this.userInterface = userInterface;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void play(Board board) {
        userInterface.displayBoard(board, currentPlayer);
        while (gameIsNotOver(board) && currentPlayer.hasMove()) {
            board = playOneRound(currentPlayer, board);
            switchPlayer(board);
            userInterface.displayBoard(board, currentPlayer);
        }
        finishGame(board);
    }

    private void switchPlayer(Board board) {
        if (gameIsNotOver(board)) {
            if (currentPlayer == player1) {
                currentPlayer = player2;
            }
            else currentPlayer = player1;
        }
    }

    private Board playOneRound(Player player, Board board) {
        return player.makeMove(board);
    }

    private void finishGame(Board board) {
        userInterface.displayBoard(board, currentPlayer);
        userInterface.announceGameEnd(currentPlayer.mark(), board);
    }

    private boolean gameIsNotOver(Board board) {
       return !board.gameOver();
    }
}
