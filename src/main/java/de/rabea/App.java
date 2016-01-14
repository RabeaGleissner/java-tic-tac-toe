package de.rabea;

import de.rabea.game.*;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App {
    public static void main( String[] args ) {
        UserInterface userInterface = new UserInterface(new RealConsole(System.in, System.out));
        Game game = new Game(userInterface, new ComputerPlayer(new RandomNumberCalc(), Mark.O), new HumanPlayer(userInterface, Mark.X));
        game.play();
    }
}
