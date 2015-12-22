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
            board.placeMark(usersPosition(board), mark);
            mark = board.switchMark(mark);
        }
        userInterface.displayBoard(board.returnCells());
        userInterface.announceGameEnd(board.switchMark(mark), rules.hasWinner());
        if (userInterface.playAgain()) {
            play();
        }
    }

    private Integer usersPosition(Board board) {
        Integer position = userInterface.returnPlayersChosenPosition(board);
        position --;
        return getValidPosition(position, board);
    }

    private Integer getValidPosition(Integer position, Board board) {
        if (board.isPositionAvailable(position)) {
            return position;
        } else {
            userInterface.positionUnavailableWarning();
            return usersPosition(board);
        }
    }
}
