package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private Player player;
    private Player opponent ;

    public Game(UserInterface userInterface, Player player, Player opponent) {
        this.userInterface = userInterface;
        this.player = player;
        this.opponent = opponent;
    }

    public void play(int boardDimension) {
        Board board = new Board(boardDimension);
        Player currentPlayer = player;
        while (gameIsNotOver(board)){
            if (player.hasMove()) {
                playOneRound(currentPlayer, board);
                if (gameIsNotOver(board)) {
                    currentPlayer = switchPlayer(currentPlayer);
                }
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
