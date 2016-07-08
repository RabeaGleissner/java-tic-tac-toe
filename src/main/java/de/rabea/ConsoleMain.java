package de.rabea;

import de.rabea.console.ConsoleUi;
import de.rabea.console.GameConsole;
import de.rabea.console.PrettyBoardPainter;
import de.rabea.game.GameRunner;
import de.rabea.player.ConsolePlayerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleMain {
    public static void main( String[] args ) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ConsoleUi userInterface = new ConsoleUi(new GameConsole(bufferedReader, System.out), new PrettyBoardPainter());
        ConsolePlayerFactory playerFactory = new ConsolePlayerFactory(userInterface);
        GameRunner gameRunner = new GameRunner(userInterface, playerFactory);
        gameRunner.setUpConsoleGameAndPlay();
    }
}
