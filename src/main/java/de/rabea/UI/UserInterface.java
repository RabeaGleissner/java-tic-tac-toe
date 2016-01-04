package de.rabea.ui;

import de.rabea.game.*;

public class UserInterface {
    private final Console console;

    public UserInterface(Console console) {
        this.console = console;
    }
    private String askUserForPosition = "Please select a position for your mark.";
    private String wantToPlayAgain = "Do you want to play again? y/n";
    private String greeting = "Welcome to Tic Tac Toe.\n ";
    private String gameOptions = "Please enter 1 if you want to play against the computer and 2 if you want to play against another human player.\n";
    private String markDistributionHvH = "The first user to play is X. The second player is O.";
    private String markDistributionHvC = "The human player is X. The computer player is O.";
    private String winnerAnnouncement = "Game over! The winner is: ";
    private String drawAnnouncement = "Game over! It's a draw.";
    private String unavailablePosition = "Sorry, this position is not available!";
    private String enterANumber = "Please enter a number between 1 and 9.";
    private InputFormatter inputFormatter = new InputFormatter();


    public void displayBoard(Cell[] cells) {
        BoardPainter boardPainter = new BoardPainter();
        console.print(boardPainter.drawBoard(cells));
    }

    public void greet() {
        console.print(greeting);
    }

    public GameMode chooseGameMode() {
        console.print(gameOptions);
        return gameMode();
    }

    public GameMode gameMode() {
        String userChoice = console.readUserInput();
        if (userChoice.equals("1")) {
            return GameMode.HvC;
        } else if (userChoice.equals("2")){
            return GameMode.HvH;
        } else {
            return chooseGameMode();
        }
    }

    public void announceMarkDistribution(GameMode gameMode) {
        if (gameMode == GameMode.HvC) {
            markDistributionForHvC();
        } else {
            markDistributionForHvH();
        }
    }

    public void announceGameEnd(Cell lastPlayedMark, boolean winner) {
        if (winner) {
            console.print(winnerAnnouncement + lastPlayedMark.toString());
        } else {
            console.print(drawAnnouncement);
        }
    }
    public Integer playersChosenPosition(Board board) {
        askForPosition();
        return getUsersPosition(board);
    }

    private Integer getUsersPosition(Board board) {
        String userInput = console.readUserInput();
        if (!(inputFormatter.isInteger(userInput)) ) {
            notANumberWarning();
            return playersChosenPosition(board);
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

    private void markDistributionForHvH() {
        console.print(markDistributionHvH);
    }

    private void markDistributionForHvC() {
        console.print(markDistributionHvC);
    }

}
