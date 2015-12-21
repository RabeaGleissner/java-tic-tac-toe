package de.rabea.ui;

import de.rabea.game.Console;

public class FakeConsole implements Console {

    private String userInput;
    private String consoleOutput;

    public FakeConsole() {
        userInput = "";
        consoleOutput = "";
    }

    public void print(String message) {
        consoleOutput = message;
    }

    public String readUserInput() {
        return userInput;
    }

    public String messagePrinted() {
        return consoleOutput;
    }

    public void userInput(String input) {
        userInput = input;
    }
}
