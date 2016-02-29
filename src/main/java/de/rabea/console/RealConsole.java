package de.rabea.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;

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
