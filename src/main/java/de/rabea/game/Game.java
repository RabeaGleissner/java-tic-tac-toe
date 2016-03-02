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
            switchPlayer();
            userInterface.displayBoard(board, currentPlayer);
        }
        finishGame(board);
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private Board playOneRound(Player player, Board board) {
        return board.placeMark(player.makeMove(board), player.mark());
    }

    private void finishGame(Board board) {
        if (board.gameOver()) {
            switchPlayer();
            userInterface.displayBoard(board, player1);
            userInterface.announceGameEnd(currentPlayer.mark(), board.hasWinner());
        }
    }

    private boolean gameIsNotOver(Board board) {
       return !board.gameOver();
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }
}
