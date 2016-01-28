package de.rabea;

import de.rabea.game.Game;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App {
    public static void main( String[] args ) {
        UserInterface userInterface = new UserInterface(new RealConsole(System.in, System.out));
        PlayerFactory playerFactory = new PlayerFactory(userInterface);
        new Game(userInterface, playerFactory).startApplication();
    }
}
