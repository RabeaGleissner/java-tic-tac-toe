package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private Player player;
    private Player opponent ;
    private final PlayerFactory playerFactory;

    public Game(UserInterface userInterface, PlayerFactory playerFactory, Player player, Player opponent) {
        this.userInterface = userInterface;
        this.player = player;
        this.opponent = opponent;
        this.playerFactory = playerFactory;
    }

    public Game(UserInterface userInterface, PlayerFactory playerFactory) {
       this(userInterface, playerFactory, playerFactory.createDefaultPlayer(), playerFactory.createDefaultPlayer());
    }

    public void startApplication() {
        userInterface.greet();
        setUpNewGame();
    }

    public void setUpNewGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        int boardDimension = userInterface.getBoardDimensionFromUser();
        userInterface.announceMarkDistribution(gameMode);
        this.player = playerFactory.createPlayer(gameMode);
        this.opponent = playerFactory.createOpponent(gameMode);
        play(boardDimension);
    }

    public void play(int boardDimension) {
        Board board = new Board(boardDimension);
        Player currentPlayer = player;
        while (gameIsNotOver(board)){
            playOneRound(currentPlayer, board);
            if (gameIsNotOver(board)) {
                currentPlayer = switchPlayer(currentPlayer);
            }
        }
        finishGame(currentPlayer.mark(), board);
        if (userInterface.playAgain()) {
            setUpNewGame();
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
