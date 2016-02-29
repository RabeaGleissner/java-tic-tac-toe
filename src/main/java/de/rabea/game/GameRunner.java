package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

public class GameRunner {
    private final UserInterface userInterface;
    private final PlayerFactory playerFactory;
    private Board board;

    public GameRunner(UserInterface userInterface, PlayerFactory playerFactory) {
        this.userInterface = userInterface;
        this.playerFactory = playerFactory;
    }

    public void setUpGameAndPlay() {
        createGame().play(board);
        replayIfRequested();
    }

    public Game createGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        board = new Board(userInterface.getBoardDimensionFromUser());
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
