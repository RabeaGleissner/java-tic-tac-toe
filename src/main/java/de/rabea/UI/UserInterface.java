package de.rabea.ui;

import de.rabea.game.Cell;
import de.rabea.game.Console;
import de.rabea.game.Replay;

public class UserInterface {
    private final Console console;
    public UserInterface(Console console) {
        this.console = console;
    }
    private String askUserForPosition = "Please select a position for your mark.";
    private String wantToPlayAgain = "Do you want to play again? y/n";
    private String greeting = "Welcome to Tic Tac Toe. The first user to play is X. The second player is O.";
    private String winnerAnnouncement = "Game over! The winner is: ";
    private String drawAnnouncement = "Game over! It's a draw.";

    public void displayBoard(Cell[] cells) {
        String boardPrinter = "";
        int i = 0;
        for (Enum cell : cells) {
            i ++;

            if (cell == Cell.EMPTY) {
                boardPrinter += i;
            } else {
                boardPrinter += cell.toString();
            }
            
            boardPrinter += " ";

            if (i%3 == 0) {
                boardPrinter += "\n";
            }
        }
        console.print(boardPrinter);
    }

    public Integer returnPlayersChosenPosition(Cell[] cells) {
        askForPosition();
        return formatUserChoiceForPosition(console.readUserInput(), cells);
    }

    public boolean playAgain() {
        askForReplay();
        return userReplayChoice(console.readUserInput());
    }

    private void askForPosition() {
        console.print(askUserForPosition);
    }

    public void askForReplay() {
        console.print(wantToPlayAgain);
    }

    private boolean userReplayChoice(String userChoice) {
        return userChoice.equals("y");
    }

    public Integer formatUserChoiceForPosition(String userChoiceForPosition, Cell[] cells) {
        try {
            Integer position = Integer.parseInt(userChoiceForPosition);
            if (position >= 1 && position <= cells.length) {
                position --;
                return position;
            }
        } catch (NumberFormatException e) {
            System.out.println("e = " + e);
            return null;
        }
        return null;
    }

    public Replay formatUserInputForReplayOption(String userInput) {
        String formatted = userInput.trim().toLowerCase();
        if (formatted.equals("y")) {
            return Replay.YES;
        } else if (formatted.equals("n")) {
            return Replay.NO;
        } else {
            return null;
        }
    }

    public void greet() {
        console.print(greeting);
    }

    public void announceGameEnd(Cell lastPlayedMark, boolean winner) {
        if (winner) {
            console.print(winnerAnnouncement + lastPlayedMark.toString());
        } else {
            console.print(drawAnnouncement);
        }

    }
}
