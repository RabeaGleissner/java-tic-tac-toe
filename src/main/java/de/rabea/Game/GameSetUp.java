package de.rabea.game;

import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class GameSetUp {
    UserInterface userInterface = new UserInterface(new RealConsole(System.in, System.out));

    public GameSetUp(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void setUpFirstGame() {
        userInterface.greet();
        setUpGame();
    }

    public void setUpGame() {
        GameMode gameMode = userInterface.chooseGameMode();
        userInterface.announceMarkDistribution(gameMode);

        if (isHumanVsComputer(gameMode)) {
            Game game = new Game(userInterface, createNewHumanPlayer(), createNewComputerPlayer(), this);
            game.play();
        } else {
            Game game = new Game(userInterface, createNewHumanPlayer(), createNewHumanOpponent(), this);
            game.play();
        }

    }

    private boolean isHumanVsComputer(GameMode gameMode) {
        return gameMode == GameMode.HvC;
    }

    private HumanPlayer createNewHumanPlayer() {
        return new HumanPlayer(userInterface, Mark.X);
    }

    private HumanPlayer createNewHumanOpponent() {
        return new HumanPlayer(userInterface, Mark.O);
    }

    public ComputerPlayer createNewComputerPlayer() {
        return new ComputerPlayer(new RandomNumberCalculator(), Mark.O);
    }
}
