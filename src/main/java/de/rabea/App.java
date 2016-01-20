package de.rabea;

import de.rabea.game.GameManager;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App {
    public static void main( String[] args ) {
        UserInterface userInterface = new UserInterface(new RealConsole(System.in, System.out));
        GameManager gameManager = new GameManager(userInterface);
        gameManager.startApplication();
    }
}
