package de.rabea.ui;

import de.rabea.game.Console;

import java.io.*;

public class RealConsole implements Console {
    BufferedReader userInput;
    PrintStream output;

    public RealConsole(InputStream userInput, PrintStream output) {
        this.userInput= new BufferedReader(new InputStreamReader(userInput));
        this.output = output;
    }

    public void print(String message) {
        output.println(message);
    }

    public String readUserInput() {
        try {
            return userInput.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
