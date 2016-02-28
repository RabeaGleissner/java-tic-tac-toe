package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private final Player player;
    private final Player opponent ;
    private Player currentPlayer;

    public Game(UserInterface userInterface, Player player, Player opponent) {
        this.userInterface = userInterface;
        this.player = player;
        this.opponent = opponent;
        this.currentPlayer = player;
    }

    public void play(Board board) {
        userInterface.displayBoard(board, currentPlayer);
        while (gameIsNotOver(board) && currentPlayer.hasMove()){
            board = playOneRound(currentPlayer, board);
            if (gameIsNotOver(board)) {
                switchPlayer();
                userInterface.displayBoard(board, currentPlayer);
            }
        }
        if (board.gameOver()) {
            finishGame(currentPlayer.mark(), board);
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == player ? opponent : player;
    }

    private Board playOneRound(Player player, Board board) {
        return board.placeMark(player.getPosition(board), player.mark());
    }

    private void finishGame(Mark mark, Board board) {
        userInterface.displayBoard(board, player);
        userInterface.announceGameEnd(mark, board.hasWinner());
    }

    private boolean gameIsNotOver(Board board) {
       return !board.gameOver();
    }

    Player getPlayer() {
        return this.player;
    }

    Player getOpponent() {
        return this.opponent;
    }
}
