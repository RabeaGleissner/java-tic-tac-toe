package de.rabea;

import de.rabea.game.Setup;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App {
    public static void main( String[] args ) {
        UserInterface userInterface = new UserInterface(new RealConsole(System.in, System.out));
        Setup setup = new Setup(userInterface, new PlayerFactory(userInterface));
        setup.startApplication();
    }
}
