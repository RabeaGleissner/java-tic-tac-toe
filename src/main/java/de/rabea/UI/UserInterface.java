package de.rabea.ui;

import de.rabea.game.Board;
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
    private String unavailablePosition = "Sorry, this position is not available!";
    private String enterANumber = "Please enter a number between 1 and 9.";
    private InputFormatter inputFormatter = new InputFormatter();

    public void displayBoard(Cell[] cells) {
        String boardImage= "";
        int i = 0;
        for (Enum cell : cells) {
            i ++;

            if (cell == Cell.EMPTY) {
                boardImage += i;
            } else {
                boardImage += cell.toString();
            }
            boardImage += " ";

            if (i%3 == 0) {
                boardImage += "\n";
            }
        }
        console.print(boardImage);
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
    public Integer returnPlayersChosenPosition(Board board) {
        askForPosition();
        return getUsersPosition(board);
    }

    private Integer getUsersPosition(Board board) {
        String userInput = console.readUserInput();
        if (!(inputFormatter.isInteger(userInput)) ) {
            notANumberWarning();
            return returnPlayersChosenPosition(board);
        } else {
            Integer position = Integer.parseInt(userInput);
            return inputFormatter.subtractOneToMatchArrayIndex(position);
        }
    }

    public boolean playAgain() {
        askForReplay();
        return userReplayChoice(console.readUserInput());
    }

    private boolean userReplayChoice(String userChoice) {
        if (inputFormatter.formatForReplayOption(userChoice) == Replay.YES) {
            return true;
        } else if (inputFormatter.formatForReplayOption(userChoice) == Replay.NO) {
            return false;
        } else {
            return playAgain();
        }
    }

    public void positionUnavailableWarning() {
        console.print(unavailablePosition);
    }

    private void askForPosition() {
        console.print(askUserForPosition);
    }

    private void askForReplay() {
        console.print(wantToPlayAgain);
    }

    private void notANumberWarning() {
        console.print(enterANumber);
    }
}
