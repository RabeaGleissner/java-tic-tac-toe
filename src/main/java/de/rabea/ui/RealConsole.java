package de.rabea.ui;

import de.rabea.game.Console;

import java.io.*;

public class RealConsole implements Console {
    private final BufferedReader bufferedReader;
    private final PrintStream output;

    public RealConsole(BufferedReader bufferedReader, PrintStream output) {
        this.bufferedReader= bufferedReader;
        this.output = output;
    }

    public void print(String message) {
        output.println(message);
    }

    public String readUserInput() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
