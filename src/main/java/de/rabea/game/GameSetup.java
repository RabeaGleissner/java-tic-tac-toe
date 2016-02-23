package de.rabea.game;

import de.rabea.player.PlayerFactory;
import de.rabea.ui.ConsoleUi;

public class GameSetup {
    private ConsoleUi userInterface;
    private PlayerFactory playerFactory;
    private Game game;

    public GameSetup(ConsoleUi userInterface, PlayerFactory playerFactory) {
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
        game.play(new Board(boardDimension));
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
