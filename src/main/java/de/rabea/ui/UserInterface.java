package de.rabea.ui;

import de.rabea.game.*;

public class UserInterface {
    private final Console console;

    public UserInterface(Console console) {
        this.console = console;
    }

    private final String ASK_FOR_POSITION = "Please select a position for your mark.";
    private final String WANT_TO_PLAY_AGAIN = "Do you want to play again? y/n";
    private final String GREETING = "Welcome to Tic Tac Toe.\n ";
    private final String GAME_OPTIONS = "Please choose the game mode. \n 1 - Human vs Human\n 2 - Human vs Computer\n 3 - Computer vs Human\n 4 - Computer vs Computer";
    private final String MARK_DISTRIBUTION_TWO_PLAYERS = "The first user to play is X. The second player is O.";
    private final String MARK_DISTRIBUTION_HVC = "The human player is X. The computer player is O.";
    private final String WINNER_ANNOUNCEMENT = "Game over! The winner is: ";
    private final String DRAW_ANNOUNCEMENT = "Game over! It's a draw.";
    private final String UNAVAILABLE_POSITION = "Sorry, this position is not available!";
    private final String ENTER_A_NUMBER = "Please enter a number between 1 and 9.";
    private final String CLEAR_SCREEN = "\033[H\033[2J";
    private InputFormatter inputFormatter = new InputFormatter();


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
        console.print(GREETING);
    }

    public GameMode getGameModeFromUser() {
        presentGameOptions();
        return gameMode();
    }

    private void presentGameOptions() {
        clearScreen();
        console.print(GAME_OPTIONS);
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
            console.print(WINNER_ANNOUNCEMENT + lastPlayedMark.toString());
        } else {
            console.print(DRAW_ANNOUNCEMENT);
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
        console.print(UNAVAILABLE_POSITION);
    }

    public void askForPosition() {
        console.print(ASK_FOR_POSITION);
    }

    private void askForReplay() {
        console.print(WANT_TO_PLAY_AGAIN);
    }

    public void notANumberWarning(Board board) {
        clearScreen();
        displayBoard(board);
        console.print(ENTER_A_NUMBER);
    }

    private void markDistributionForHvH() {
        console.print(MARK_DISTRIBUTION_TWO_PLAYERS);
    }

    private void markDistributionForHvC() {
        console.print(MARK_DISTRIBUTION_HVC);
    }
    public String clearScreenCharacters() {
        return CLEAR_SCREEN;
    }
}
