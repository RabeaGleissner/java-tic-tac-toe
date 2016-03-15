package de.rabea.game;

import de.rabea.player.PlayerFactory;

public class GameFactory {

    private UserInterface ui;
    private PlayerFactory playerFactory;

    public GameFactory(UserInterface ui, PlayerFactory playerFactory) {
        this.ui = ui;
        this.playerFactory = playerFactory;
    }

    public Game createGame(GameMode gameMode) {
        return new Game(ui, playerFactory.createPlayer1(gameMode), playerFactory.createPlayer2(gameMode));
    }
}
