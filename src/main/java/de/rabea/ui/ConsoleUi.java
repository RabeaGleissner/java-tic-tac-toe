package de.rabea.ui;

import de.rabea.game.*;

public class ConsoleUi implements UserInterface {
    private final Console console;
    private final BoardPainter boardPainter;

    public ConsoleUi(Console console, BoardPainter boardPainter) {
        this.console = console;
        this.boardPainter = boardPainter;
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
    private final String BOARD_SIZE_OPTIONS = "Choose a board size: \n 3 - 3x3 board \n 4 - 4x4 board" ;
    private final String BOARD_SIZE_WARNING = "Please enter either 3 for a 3x3 Board and 4 for a 4x4 Board";
    private final InputFormatter inputFormatter = new InputFormatter();


    @Override
    public void displayBoard(Board board) {
        clearScreen();
        console.print(boardPainter.drawBoard(board));
    }

    private void clearScreen() {
        console.print(clearScreenCharacters());
    }

    public void greet() {
        clearScreen();
        console.print(GREETING);
    }

    @Override
    public GameMode getGameModeFromUser() {
        presentGameOptions();
        return gameMode();
    }

    @Override
    public int getBoardDimensionFromUser() {
        presentAvailableDimensions();
        return selectedBoardSize();
    }

    private int selectedBoardSize() {
        String userChoice = console.readUserInput();
        switch (userChoice) {
            case "3":
                return 3;
            case "4":
                return 4;
            default:
                console.print(BOARD_SIZE_WARNING);
                return getBoardDimensionFromUser();
        }
    }

    private void presentAvailableDimensions() {
        clearScreen();
        console.print(BOARD_SIZE_OPTIONS);
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

    @Override
    public void announceGameEnd(Mark lastPlayedMark, boolean winner) {
        if (winner) {
            console.print(WINNER_ANNOUNCEMENT + lastPlayedMark.toString());
        } else {
            console.print(DRAW_ANNOUNCEMENT);
        }
    }

    public boolean isUserInputInvalid(String userInput) {
        return !(inputFormatter.isInteger(userInput));
    }

    public int getFormattedUserPosition(String userInput) {
        int position = Integer.parseInt(userInput);
        return inputFormatter.subtractOneToMatchArrayIndex(position);
    }

    public String readUserInput() {
        return console.readUserInput();
    }

    @Override
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
    private String clearScreenCharacters() {
        return CLEAR_SCREEN;
    }
}
