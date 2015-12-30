package de.rabea.ui;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RealConsoleTest {


    @Test
    public void readsAGivenStringFromTheConsole() {
        InputStream input = new ByteArrayInputStream("hello\n".getBytes());
        RealConsole realConsole = new RealConsole(input, null);
        assertEquals("hello", realConsole.readUserInput());
    }

    @Test
    public void printsAGivenStringToTheConsole() {
        InputStream input = new ByteArrayInputStream("0\n".getBytes());
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printedOutput = new PrintStream(output);
        RealConsole realConsole = new RealConsole(input, printedOutput);
        realConsole.print("hey");
        assertEquals("hey\n", output.toString());
    }
}