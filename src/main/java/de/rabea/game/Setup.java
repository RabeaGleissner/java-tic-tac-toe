package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

public class Setup {
    private UserInterface userInterface;
    private PlayerFactory playerFactory;

    public Setup(UserInterface userInterface, PlayerFactory playerFactory) {
        this.userInterface = userInterface;
        this.playerFactory = playerFactory;
    }

    public void startApplication() {
        userInterface.greet();
        playANewGame();
    }

    public void playANewGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        userInterface.announceMarkDistribution(gameMode);

        Game game = new Game(userInterface, playerFactory.createPlayer(gameMode), playerFactory.createOpponent(gameMode), this);
        game.play();
    }
}
