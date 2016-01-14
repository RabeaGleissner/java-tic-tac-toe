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
            userInterface.notANumberWarning();
            return getPosition(board);
        } else {
            return userInterface.getFormattedUserPosition(userInput);

        }
    }
}
