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
    private String gameOptions = "Please choose the game mode. \n 1 - Human vs Human\n 2 - Human vs Computer\n 3 - Computer vs Human\n 4 - Computer vs Computer";
    private String markDistributionHvH = "The first user to play is X. The second player is O.";
    private String markDistributionHvC = "The human player is X. The computer player is O.";
    private String winnerAnnouncement = "Game over! The winner is: ";
    private String drawAnnouncement = "Game over! It's a draw.";
    private String unavailablePosition = "Sorry, this position is not available!";
    private String enterANumber = "Please enter a number between 1 and 9.";
    private InputFormatter inputFormatter = new InputFormatter();
    private String clearScreen= "\033[H\033[2J";


    public void displayBoard(Board board) {
        clearScreen();
        BoardPainter boardPainter = new BoardPainter(board);
        console.print(boardPainter.drawBoard());
    }

    private void clearScreen() {
        console.print(clearScreenCharacters());
    }

    public void greet() {
        clearScreen();
        console.print(greeting);
    }

    public GameMode getGameModeFromUser() {
        presentGameOptions();
        return gameMode();
    }

    private void presentGameOptions() {
        clearScreen();
        console.print(gameOptions);
    }

    private GameMode gameMode() {
        String userChoice = console.readUserInput();
        switch (userChoice) {
            case "1":
                return GameMode.HumanVsHuman;
            case "2":
                return GameMode.HumanVsComputer;
            case "3":
                return GameMode.ComputerVsHuman;
            case "4":
                return GameMode.ComputerVsComputer;
            default:
                return getGameModeFromUser();
        }
    }

    public void announceMarkDistribution(GameMode gameMode) {
        if (gameMode == GameMode.HumanVsComputer) {
            markDistributionForHvC();
        } else {
            markDistributionForHvH();
        }
    }

    public void announceGameEnd(Mark lastPlayedMark, boolean winner) {
        if (winner) {
            console.print(winnerAnnouncement + lastPlayedMark.toString());
        } else {
            console.print(drawAnnouncement);
        }
    }

    public boolean isUserInputInvalid(String userInput) {
       return userInputIsNotANumber(userInput);
    }

    private boolean userInputIsNotANumber(String userInput) {
        return !(inputFormatter.isInteger(userInput));
    }

    public int getFormattedUserPosition(String userInput) {
        int position = Integer.parseInt(userInput);
        return inputFormatter.subtractOneToMatchArrayIndex(position);
    }

    public String readUserInput() {
        return console.readUserInput();
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

    public void positionUnavailableWarning(Board board) {
        displayBoard(board);
        console.print(unavailablePosition);
    }

    public void askForPosition() {
        console.print(askUserForPosition);
    }

    private void askForReplay() {
        console.print(wantToPlayAgain);
    }

    public void notANumberWarning(Board board) {
        clearScreen();
        displayBoard(board);
        console.print(enterANumber);
    }

    private void markDistributionForHvH() {
        console.print(markDistributionHvH);
    }

    private void markDistributionForHvC() {
        console.print(markDistributionHvC);
    }
    public String clearScreenCharacters() {
        return clearScreen;
    }
}
