package de.rabea.console;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class GameConsoleTest {


    @Test
    public void readsAGivenStringFromTheConsole() {
        InputStream input = new ByteArrayInputStream("hello\n".getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        GameConsole gameConsole = new GameConsole(bufferedReader, null);
        assertEquals("hello", gameConsole.readUserInput());
    }

    @Test
    public void printsAGivenStringToTheConsole() {
        InputStream input = new ByteArrayInputStream("0\n".getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        OutputStream output = new ByteArrayOutputStream();
        PrintStream printedOutput = new PrintStream(output);
        GameConsole gameConsole = new GameConsole(bufferedReader, printedOutput);
        gameConsole.print("hey");
        assertEquals("hey\n", output.toString());
    }

    @Test(expected=UncheckedIOException.class)
    public void throwsAnExceptionWhenBufferedReaderIsClosed() throws IOException {
        BufferedReader closedBufferedReader = closedBufferedReader();
        GameConsole gameConsole = new GameConsole(closedBufferedReader, null);
        gameConsole.readUserInput();
    }

    private BufferedReader closedBufferedReader() throws IOException {
        InputStream input = new ByteArrayInputStream("\n".getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        bufferedReader.close();
        return bufferedReader;
    }
}