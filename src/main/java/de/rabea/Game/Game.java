package de.rabea.game;

import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class Game {

    UserInterface userInterface = new UserInterface(new RealConsole());

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void play() {
        userInterface.greet();
    }
}
