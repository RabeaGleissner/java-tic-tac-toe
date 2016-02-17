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

    public void start() {
        userInterface.greet();
        setUpGameAndPlay();
    }

    public void setUpGameAndPlay() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        int boardDimension = userInterface.getBoardDimensionFromUser();
        userInterface.announceMarkDistribution(gameMode);
        game = createGame(gameMode);
        game.play(boardDimension);
        giveReplayOption();
    }

    private Game createGame(GameMode gameMode) {
        Player player = playerFactory.createPlayer(gameMode);
        Player opponent = playerFactory.createOpponent(gameMode);
        return new Game(userInterface, player, opponent);
    }

    private void giveReplayOption() {
        if (userInterface.playAgain()) {
            setUpGameAndPlay();
        }
    }

    Player getPlayer() {
        return game.getPlayer();
    }

    Player getOpponent() {
        return game.getOpponent();
    }
}
