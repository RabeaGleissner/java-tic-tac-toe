package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private final Player player;
    private final Player opponent ;
    private final PlayerFactory playerFactory;

    public Game(UserInterface userInterface, PlayerFactory playerFactory, Player player, Player opponent) {
        this.userInterface = userInterface;
        this.player = player;
        this.opponent = opponent;
        this.playerFactory = playerFactory;
    }

    public void startApplication() {
        userInterface.greet();
        setUpNewGame();
    }

    public void setUpNewGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        userInterface.announceMarkDistribution(gameMode);

        Game game = new Game(userInterface, playerFactory, playerFactory.createPlayer(gameMode), playerFactory.createOpponent(gameMode));
        game.play();
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
        board.placeMark(player.getPosition(board), player.mark());
    }

    private void finishGame(Mark mark, Board board) {
        userInterface.displayBoard(board);
        userInterface.announceGameEnd(mark.switchMark(mark), board.hasWinner());
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
