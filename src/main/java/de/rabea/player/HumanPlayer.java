package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;
import de.rabea.ui.UserInterface;

public class HumanPlayer implements Player {
    UserInterface userInterface;
    Mark mark;

    public HumanPlayer(UserInterface userInterface, Mark mark) {
        this.userInterface = userInterface;
        this.mark = mark;
    }

    @Override
    public Mark mark() {
        return mark;
    }

    @Override
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
        userInterface.notANumberWarning(board);
        return getPosition(board);
    }

    private int getDifferentUserInputIfPositionUnavailable(Board board) {
        userInterface.positionUnavailableWarning(board);
        return getPosition(board);
    }
}
