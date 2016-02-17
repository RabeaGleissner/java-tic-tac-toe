package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

public class GameRunner {
    private UserInterface userInterface;
    private PlayerFactory playerFactory;
    private Game game;

    public GameRunner(UserInterface userInterface, PlayerFactory playerFactory) {
        this.userInterface = userInterface;
        this.playerFactory = playerFactory;
    }

    public void setUpGameAndPlay() {
        game = createGame();
        game.play();
        replayIfRequested();
    }

    private Game createGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        int boardDimension = userInterface.getBoardDimensionFromUser();
        Board board = new Board(boardDimension);
        userInterface.announceMarkDistribution(gameMode);
        return new Game(userInterface, playerFactory.createPlayer(gameMode),
                playerFactory.createOpponent(gameMode), board);
    }

    private void replay() {
        setUpGameAndPlay();
    }

    private void replayIfRequested() {
        if (userInterface.playAgain()) {
            replay();
        }
    }

    Player getPlayer() {
        return game.getPlayer();
    }

    Player getOpponent() {
        return game.getOpponent();
    }
}
