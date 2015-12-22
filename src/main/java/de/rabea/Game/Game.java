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
        Cell mark = Cell.X;
        while (!rules.gameOver()){
            userInterface.displayBoard(board.returnCells());
            Integer usersChosenPosition = userInterface.returnUserChoiceForPosition(board.returnCells());
            board.placeMark(usersChosenPosition, mark);
            mark = board.switchMark(mark);
        }
    }
}
