package de.rabea.game;

import de.rabea.player.GuiPlayer;
import de.rabea.player.UnbeatableComputerPlayer;

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
            if (gameIsNotOver(board)) {
                switchPlayer();
                updateView(board);
            }
        }
        if (board.gameOver()) {
            finishGame(currentPlayer.mark(), board);
        }
    }

    private void updateView(Board board) {
        if (!(currentPlayer instanceof UnbeatableComputerPlayer && player1 instanceof GuiPlayer)) {
            userInterface.displayBoard(board, currentPlayer);
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private Board playOneRound(Player player, Board board) {
        return board.placeMark(player.getPosition(board), player.mark());
    }

    private void finishGame(Mark mark, Board board) {
        userInterface.displayBoard(board, player1);
        userInterface.announceGameEnd(mark, board.hasWinner());
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
