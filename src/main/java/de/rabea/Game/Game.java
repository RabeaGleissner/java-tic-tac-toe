package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private final Player player;
    private final Player opponent ;
    private final GameManager gameManager;

    public Game(UserInterface userInterface, Player player, Player opponent, GameManager gameManager) {
        this.userInterface = userInterface;
        this.player = player;
        this.opponent = opponent;
        this.gameManager = gameManager;
    }

    public void play() {
        Board board = new Board();
        Player currentPlayer = player;
        while (gameIsNotOver(board)){
            playOneRound(currentPlayer, board);
            currentPlayer = switchPlayer(currentPlayer);
        }
        finishGame(currentPlayer.mark(), board);
        if (userInterface.playAgain()) {
            gameManager.playANewGame();
        }
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
        board.placeMark(player.getPosition(board), player.mark());
    }

    private void finishGame(Mark mark, Board board) {
        userInterface.displayBoard(board);
        userInterface.announceGameEnd(mark.switchMark(mark), board.hasWinner());
    }

    private boolean gameIsNotOver(Board board) {
       return !board.gameOver();
    }
}
