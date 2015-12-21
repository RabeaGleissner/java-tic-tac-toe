package de.rabea;

import java.util.Scanner;

public class RealConsole implements Console {

    public void print(String message) {
        System.out.println(message);
    }

    public String readUserInput() {
        Scanner userInput = new Scanner(System.in);
        return userInput.next();
    }
}
