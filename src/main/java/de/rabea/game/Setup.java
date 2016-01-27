package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Setup {
    private UserInterface userInterface;

    public Setup(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void startApplication() {
        userInterface.greet();
        playANewGame();
    }

    public void playANewGame() {
        GameType gameType = new GameType(userInterface, this);
        Game game = gameType.createGame();
        game.play();
    }
}
