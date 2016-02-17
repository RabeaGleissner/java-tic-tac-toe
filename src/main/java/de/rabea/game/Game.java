package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private Player player;
    private Player opponent ;
    private Board board;

    public Game(UserInterface userInterface, Player player, Player opponent,  Board board) {
        this.userInterface = userInterface;
        this.player = player;
        this.opponent = opponent;
        this.board = board;
    }

    public void play() {
        Player currentPlayer = player;
        while (gameIsNotOver(board)){
            playOneRound(currentPlayer, board);
            if (gameIsNotOver(board)) {
                currentPlayer = switchPlayer(currentPlayer);
            }
        }
        finishGame(currentPlayer.mark(), board);
    }

    public Player switchPlayer(Player currentPlayer) {
        if (currentPlayer == player) {
            return opponent;
        } else {
            return player;
        }
    }

    private void playOneRound(Player player, Board board) {
        userInterface.displayBoard(board);
        board.placeMarkOnExistingBoard(player.getPosition(board), player.mark());
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
