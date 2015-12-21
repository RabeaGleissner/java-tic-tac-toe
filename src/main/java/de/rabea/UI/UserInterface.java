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
    private String playAgain = "Do you want to play again? y/n";

    public String displayBoard(Cell[] cells) {
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
        return boardPrinter;
    }

    public void askForPosition() {
        console.print(askUserForPosition);
    }

    public String playAgain() {
        return playAgain;
    }

    public boolean wantsToPlayAgain(String userChoice) {
        return userChoice.equals("y");
    }

    public Integer formatUserChoiceForPosition(String userChoiceForPosition) {
        try {
            Integer position = Integer.parseInt(userChoiceForPosition);
            if (position >= 1 && position <= 8) {
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
}
