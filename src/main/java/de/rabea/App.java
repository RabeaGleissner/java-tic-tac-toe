package de.rabea;

import de.rabea.game.ComputerPlayer;
import de.rabea.game.Game;
import de.rabea.game.HumanPlayer;
import de.rabea.game.RandomNumberCalc;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App {
    public static void main( String[] args ) {
        UserInterface userInterface = new UserInterface(new RealConsole(System.in, System.out));
        Game game = new Game(userInterface, new ComputerPlayer(new RandomNumberCalc()), new HumanPlayer(userInterface));
        game.play();
    }
}
