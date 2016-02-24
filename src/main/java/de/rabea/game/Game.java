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
        userInterface.displayBoard(board);
        while (gameIsNotOver(board) && currentPlayer.hasMove()){
            playOneRound(currentPlayer, board);
            if (gameIsNotOver(board)) {
                switchPlayer();
            }
        }
        if (board.gameOver()) {
            finishGame(currentPlayer.mark(), board);
        }
    }

    private void switchPlayer() {
        if (currentPlayer == player) {
            currentPlayer = opponent;
        } else {
            currentPlayer = player;
        }
    }

    private void playOneRound(Player player, Board board) {
        board.placeMarkOnExistingBoard(player.getPosition(board), player.mark());
        userInterface.displayBoard(board);
    }

    private void finishGame(Mark mark, Board board) {
        userInterface.displayBoard(board);
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
