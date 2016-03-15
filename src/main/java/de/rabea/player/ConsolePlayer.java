package de.rabea.player;

import de.rabea.console.ConsoleUi;
import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class ConsolePlayer extends Player {
    private final ConsoleUi userInterface;

    public ConsolePlayer(ConsoleUi userInterface, Mark mark) {
        super(mark);
        this.userInterface = userInterface;
    }

    @Override
    public int getMove(Board board) {
        userInterface.askForPosition();
        return returnUsersPosition(board);
    }

    @Override
    public boolean hasMove() {
        return true;
    }

    @Override
    public Board makeMove(Board board) {
        return board.placeMark(getMove(board), this.mark);
    }

    private int returnUsersPosition(Board board) {
        String userInput = userInterface.readUserInput();
        if (userInterface.isUserInputInvalid(userInput)) {
            return getDifferentUserInputIfInputIsBad(board);
        } else {
            int usersChosenPosition = userInterface.getFormattedUserPosition(userInput);
            if (!board.isPositionAvailable(usersChosenPosition)) {
                return getDifferentUserInputIfPositionUnavailable(board);
            }
            return usersChosenPosition;
        }
    }

    private int getDifferentUserInputIfInputIsBad(Board board) {
        userInterface.notANumberWarning(board);
        return getMove(board);
    }

    private int getDifferentUserInputIfPositionUnavailable(Board board) {
        userInterface.positionUnavailableWarning(board);
        return getMove(board);
    }
}
