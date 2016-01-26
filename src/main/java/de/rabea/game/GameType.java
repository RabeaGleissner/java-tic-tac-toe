package de.rabea.game;

import de.rabea.player.HumanPlayer;
import de.rabea.player.UnbeatableComputerPlayer;
import de.rabea.ui.UserInterface;

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

        if (isHumanVsComputer(gameMode)) {
            return humanVsComputerGame();
        } else if (isComputerVsHumanGame(gameMode)) {
            return computerVsHumanGame();
        } else if (isComputerVsComputerGame(gameMode)) {
            return computerVsComputerGame();
        } else {
            return humanVsHumanGame();
        }
    }

    private Game humanVsHumanGame() {
        return new Game(userInterface, newHumanPlayer(), newHumanOpponent(), gameManager);
    }

    private Game humanVsComputerGame() {
        return new Game(userInterface, newHumanPlayer(), newComputerOpponent(), gameManager);
    }

    private Game computerVsHumanGame() {
        return new Game(userInterface, newComputerPlayer(), newHumanOpponent(), gameManager);
    }

    private Game computerVsComputerGame() {
        return new Game(userInterface, newComputerPlayer(), newComputerOpponent(), gameManager);
    }

    private HumanPlayer newHumanPlayer() {
        return new HumanPlayer(userInterface, X);
    }

    private HumanPlayer newHumanOpponent() {
        return new HumanPlayer(userInterface, O);
    }

    private UnbeatableComputerPlayer newComputerPlayer() {
        return new UnbeatableComputerPlayer(X);
    }

    private UnbeatableComputerPlayer newComputerOpponent() {
        return new UnbeatableComputerPlayer(O);
    }

    private boolean isHumanVsComputer(GameMode gameMode) {
        return gameMode == GameMode.HvC;
    }

    private boolean isComputerVsComputerGame(GameMode gameMode) {
        return gameMode == GameMode.CvC;
    }

    private boolean isComputerVsHumanGame(GameMode gameMode) {
        return gameMode == GameMode.CvH;
    }
}
