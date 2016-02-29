package de.rabea;

import de.rabea.game.GameRunner;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.ConsoleUi;
import de.rabea.ui.PrettyBoardPainter;
import de.rabea.ui.RealConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleMain {
    public static void main( String[] args ) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ConsoleUi userInterface = new ConsoleUi(new RealConsole(bufferedReader, System.out), new PrettyBoardPainter());
        PlayerFactory playerFactory = new PlayerFactory(userInterface);
        GameRunner gameRunner = new GameRunner(userInterface, playerFactory);
        gameRunner.setUpConsoleGameAndPlay();
    }
}
