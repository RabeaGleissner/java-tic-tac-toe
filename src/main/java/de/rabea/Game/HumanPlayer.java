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
        return returnUsersPositionOrAskAgain(board);
    }

    public int returnUsersPositionOrAskAgain(Board board) {
        String userInput = userInterface.readUserInput();
        if (userInterface.isUserInputInvalid(userInput)) {
            return askAgainIfInputIsBad(board);
        } else {
            int usersChosenPosition = userInterface.getFormattedUserPosition(userInput);
            if (!board.isPositionAvailable(usersChosenPosition)) {
                return askAgainIfPositionUnavailable(board);
            }
            return usersChosenPosition;
        }
    }

    private int askAgainIfInputIsBad(Board board) {
        userInterface.notANumberWarning();
        return getPosition(board);
    }

    private int askAgainIfPositionUnavailable(Board board) {
        userInterface.positionUnavailableWarning();
        return getPosition(board);
    }
}
