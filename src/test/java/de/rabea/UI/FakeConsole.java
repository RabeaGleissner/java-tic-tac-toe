package de.rabea.ui;

import de.rabea.game.Console;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeConsole implements Console {

    private List<String> userInput;
    private String consoleOutput;

    public FakeConsole() {
        userInput = new LinkedList<String>();
        consoleOutput = "";
    }

    public void print(String message) {
        consoleOutput = message;
    }

    public String readUserInput() {
        return userInput.remove(0);
    }

    public String messagePrinted() {
        return consoleOutput;
    }

    public void userInput(String ... input) {
        userInput.addAll(Arrays.asList(input));
    }
}
