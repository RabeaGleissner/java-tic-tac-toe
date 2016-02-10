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
        setUpGame();
    }

    public void setUpGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        int boardDimension = userInterface.getBoardDimensionFromUser();
        userInterface.announceMarkDistribution(gameMode);
        game = createGame(gameMode);
        game.play(boardDimension);
        playAnotherGame();
    }

    private Game createGame(GameMode gameMode) {
        Player player = playerFactory.createPlayer(gameMode);
        Player opponent = playerFactory.createOpponent(gameMode);
        return new Game(userInterface, player, opponent);
    }

    private void playAnotherGame() {
        if (userInterface.playAgain()) {
            setUpGame();
        }
    }

    Player getPlayer() {
        return game.getPlayer();
    }

    Player getOpponent() {
        return game.getOpponent();
    }

}
