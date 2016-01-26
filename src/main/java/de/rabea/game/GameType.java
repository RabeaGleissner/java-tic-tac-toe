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
        } else {
            return createHumanVsHumanGame();
        }
    }

    private Game createHumanVsHumanGame() {
        return new Game(userInterface, createNewHumanPlayer(), createNewHumanOpponent(), gameManager);
    }

    private Game humanVsComputerGame() {
        return new Game(userInterface, createNewHumanPlayer(), createNewComputerPlayer(), gameManager);
    }

    private boolean isHumanVsComputer(GameMode gameMode) {
        return gameMode == GameMode.HvC;
    }

    private HumanPlayer createNewHumanPlayer() {
        return new HumanPlayer(userInterface, X);
    }

    private HumanPlayer createNewHumanOpponent() {
        return new HumanPlayer(userInterface, O);
    }

    private UnbeatableComputerPlayer createNewComputerPlayer() {
//        return new ComputerPlayer(new RandomNumberCalculator(), O);
        return new UnbeatableComputerPlayer(O);
    }
}
