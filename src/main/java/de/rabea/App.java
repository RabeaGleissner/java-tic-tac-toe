package de.rabea;

import de.rabea.game.*;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App {
    public static void main( String[] args ) {
        UserInterface userInterface = new UserInterface(new RealConsole(System.in, System.out));
        GameSetUp gameSetUp = new GameSetUp(userInterface);
        gameSetUp.setUpFirstGame();
    }
}
