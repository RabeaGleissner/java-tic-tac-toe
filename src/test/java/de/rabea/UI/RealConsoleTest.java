package de.rabea.ui;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class RealConsoleTest {


    @Test
    public void readsAGivenStringFromTheConsole() {
        InputStream input = new ByteArrayInputStream("hello\n".getBytes());
        RealConsole realConsole = new RealConsole(input, null);
        assertEquals("hello", realConsole.readUserInput());
    }
}