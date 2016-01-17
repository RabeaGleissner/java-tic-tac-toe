package de.rabea.game;

import de.rabea.ui.UserInterface;

public class HumanPlayer implements Player {
    UserInterface userInterface;
    Mark mark;

    public HumanPlayer(UserInterface userInterface, Mark mark) {
        this.userInterface = userInterface;
        this.mark = mark;
    }

    public Mark mark() {
        return mark;
    }

    public int getPosition(Board board) {
        userInterface.askForPosition();
        return returnUsersPosition(board);
    }

    public int returnUsersPosition(Board board) {
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
        userInterface.notANumberWarning();
        return getPosition(board);
    }

    private int getDifferentUserInputIfPositionUnavailable(Board board) {
        userInterface.positionUnavailableWarning();
        return getPosition(board);
    }
}
