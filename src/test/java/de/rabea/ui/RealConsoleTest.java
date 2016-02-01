package de.rabea.ui;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RealConsoleTest {


    @Test
    public void readsAGivenStringFromTheConsole() {
        InputStream input = new ByteArrayInputStream("hello\n".getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        RealConsole realConsole = new RealConsole(bufferedReader, null);
        assertEquals("hello", realConsole.readUserInput());
    }

    @Test
    public void printsAGivenStringToTheConsole() {
        InputStream input = new ByteArrayInputStream("0\n".getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printedOutput = new PrintStream(output);
        RealConsole realConsole = new RealConsole(bufferedReader, printedOutput);
        realConsole.print("hey");
        assertEquals("hey\n", output.toString());
    }

    @Test(expected=UncheckedIOException.class)
    public void throwsAnExceptionForBadInput() throws IOException {
        InputStream input = new ByteArrayInputStream("hello\n".getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        bufferedReader.close();
        RealConsole realConsole = new RealConsole(bufferedReader, null);
        realConsole.readUserInput();
    }
}