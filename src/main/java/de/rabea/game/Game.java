package de.rabea.game;

import de.rabea.gui.GuiPlayer;
import de.rabea.player.UnbeatableComputerPlayer;

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
                updateView(board);
            }
        }
        if (board.gameOver()) {
            finishGame(currentPlayer.mark(), board);
        }
    }

    private void updateView(Board board) {
        if (!(currentPlayer instanceof UnbeatableComputerPlayer && player instanceof GuiPlayer)) {
            userInterface.displayBoard(board, currentPlayer);
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

    public Player getPlayer() {
        return this.player;
    }

    public Player getOpponent() {
        return this.opponent;
    }
}
