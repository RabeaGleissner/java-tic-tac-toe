package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.ConsoleUi;

public class GameRunner {
    private ConsoleUi userInterface;
    private PlayerFactory playerFactory;
    private Board board;

    public GameRunner(ConsoleUi userInterface, PlayerFactory playerFactory) {
        this.userInterface = userInterface;
        this.playerFactory = playerFactory;
    }

    public void setUpGameAndPlay() {
        Game game = createGame();
        game.play(board);
        replayIfRequested();
    }

    public Game createGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        int boardDimension = userInterface.getBoardDimensionFromUser();
        board = new Board(boardDimension);
        userInterface.announceMarkDistribution(gameMode);
        return new Game(userInterface, playerFactory.createPlayer(gameMode),
                playerFactory.createOpponent(gameMode));
    }

    private void replay() {
        setUpGameAndPlay();
    }

    private void replayIfRequested() {
        if (userInterface.playAgain()) {
            replay();
        }
    }
}
