package de.rabea.game;

import de.rabea.player.HumanPlayer;
import de.rabea.player.UnbeatableComputerPlayer;
import de.rabea.ui.UserInterface;

import static de.rabea.game.GameMode.*;
import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;

public class GameType {
    private UserInterface userInterface;
    private GameManager gameManager;

    public GameType(UserInterface userInterface, GameManager gameManager) {
        this.userInterface = userInterface;
        this.gameManager = gameManager;
    }

    public Game createGame() {
        GameMode gameMode = userInterface.getGameModeFromUser();
        userInterface.announceMarkDistribution(gameMode);

        return new Game(userInterface, createPlayer(gameMode), createOpponent(gameMode), gameManager);
    }

    private Player createPlayer(GameMode gameMode) {
        if (gameMode == HumanVsHuman || gameMode == HumanVsComputer) {
           return new HumanPlayer(userInterface, X);
        } else {
           return new UnbeatableComputerPlayer(X);
        }
    }

    private Player createOpponent(GameMode gameMode) {
        if (gameMode == HumanVsComputer || gameMode == ComputerVsComputer) {
            return new UnbeatableComputerPlayer(O);
        } else {
            return new HumanPlayer(userInterface, O);
        }
    }
}
