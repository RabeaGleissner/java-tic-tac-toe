package de.rabea.game;

import de.rabea.player.ComputerPlayer;
import de.rabea.player.HumanPlayer;
import de.rabea.player.RandomNumberCalculator;
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
        GameMode gameMode = userInterface.chooseGameMode();
        userInterface.announceMarkDistribution(gameMode);

        if (isHumanVsComputer(gameMode)) {
            return new Game(userInterface, createNewHumanPlayer(), createNewComputerPlayer(), gameManager);
        } else {
            return new Game(userInterface, createNewHumanPlayer(), createNewHumanOpponent(), gameManager);
        }
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

    public ComputerPlayer createNewComputerPlayer() {
        return new ComputerPlayer(new RandomNumberCalculator(), O);
    }
}
