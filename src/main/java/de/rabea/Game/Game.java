package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void play() {
        userInterface.greet();
        Cell mark = Cell.X;
        Board board = new Board();
        Rules rules = new Rules(board);
        while (!rules.gameOver()){
            userInterface.displayBoard(board.returnCells());
            Integer position = userInterface.returnPlayersChosenPosition(board.returnCells());
            board.placeMark(position, mark);
            mark = board.switchMark(mark);
        }
        userInterface.displayBoard(board.returnCells());
        userInterface.announceGameEnd(board.switchMark(mark), rules.hasWinner());
        if (userInterface.playAgain()) {
            play();
        }
    }
}