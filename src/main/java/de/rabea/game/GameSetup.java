package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

public class GameSetup {
    private UserInterface userInterface;
    private PlayerFactory playerFactory;
    private Game game;

    public GameSetup(UserInterface userInterface, PlayerFactory playerFactory) {
        this.userInterface = userInterface;
        this.playerFactory = playerFactory;
    }

    public void startApplication() {
        userInterface.greet();
        setUpNewGame();
    }

    public void setUpNewGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        int boardDimension = userInterface.getBoardDimensionFromUser();
        userInterface.announceMarkDistribution(gameMode);
        Player player = playerFactory.createPlayer(gameMode);
        Player opponent = playerFactory.createOpponent(gameMode);
        game = new Game(userInterface, playerFactory, player, opponent);
        game.play(boardDimension);
    }

    Player getPlayer() {
        return game.getPlayer();
    }

    Player getOpponent() {
        return game.getOpponent();
    }

}
