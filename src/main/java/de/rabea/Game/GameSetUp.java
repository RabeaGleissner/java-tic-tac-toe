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

        if (gameMode == GameMode.HvC) {
            Game game = new Game(userInterface, new HumanPlayer(userInterface, Mark.X), new ComputerPlayer(new RandomNumberCalc(), Mark.O), this);
            game.play();
        } else {
            Game game = new Game(userInterface, new HumanPlayer(userInterface, Mark.X), new HumanPlayer(userInterface, Mark.O), this);
            game.play();
        }

    }
}
