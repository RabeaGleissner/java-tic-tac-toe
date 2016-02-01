package de.rabea.ui;

import de.rabea.game.Console;

import java.io.*;

public class RealConsole implements Console {
    BufferedReader bufferedReader;
    BufferedReader userInput;
    PrintStream output;

    public RealConsole(BufferedReader bufferedReader, InputStream userInput, PrintStream output) {
        this.bufferedReader= bufferedReader;
//        this.userInput= new BufferedReader(new InputStreamReader(userInput));
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
