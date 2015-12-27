package de.rabea;

import de.rabea.game.Game;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App {
    public static void main( String[] args ) {
        Game game = new Game(new UserInterface(new RealConsole()));
        game.play();
    }
}
