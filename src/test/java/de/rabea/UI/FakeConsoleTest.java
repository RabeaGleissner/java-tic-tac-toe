package de.rabea.ui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FakeConsoleTest {
    private FakeConsole fakeConsole;

    @Before
    public void setup() {
        fakeConsole = new FakeConsole();
    }

    @Test
    public void printMessageToConsole() {
        String prompt = "message to user";
        fakeConsole.print(prompt);
        assertEquals(prompt, fakeConsole.messagePrinted());
    }

    @Test
    public void readUserInput() {
        String input = "1";
        fakeConsole.userInput(input);
        assertEquals(input, fakeConsole.readUserInput());
    }
}