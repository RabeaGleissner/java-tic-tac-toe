package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private final Board board;
    private final Rules rules;

    public Game(UserInterface userInterface, Board board, Rules rules) {
        this.userInterface = userInterface;
        this.board = board;
        this.rules = rules;
    }

    public void play() {
        userInterface.greet();
        userInterface.displayBoard(board.returnCells());
        userInterface.askForPosition();
    }
}
