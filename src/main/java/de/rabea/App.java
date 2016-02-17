package de.rabea;

import de.rabea.game.GameRunner;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.PrettyBoardPainter;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main( String[] args ) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInterface userInterface = new UserInterface(new RealConsole(bufferedReader, System.out), new PrettyBoardPainter());
        PlayerFactory playerFactory = new PlayerFactory(userInterface);
        GameRunner gameRunner = new GameRunner(userInterface, playerFactory);
        gameRunner.start();
    }
}
