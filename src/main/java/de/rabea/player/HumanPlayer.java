package de.rabea.player;

import de.rabea.console.ConsoleUi;
import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class HumanPlayer extends Player {
    private final ConsoleUi userInterface;

    public HumanPlayer(ConsoleUi userInterface, Mark mark) {
        super(mark);
        this.userInterface = userInterface;
    }

    @Override
    public int makeMove(Board board) {
        userInterface.askForPosition();
        return returnUsersPosition(board);
    }

    @Override
    public boolean hasMove() {
        return true;
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
        return makeMove(board);
    }

    private int getDifferentUserInputIfPositionUnavailable(Board board) {
        userInterface.positionUnavailableWarning(board);
        return makeMove(board);
    }
}
