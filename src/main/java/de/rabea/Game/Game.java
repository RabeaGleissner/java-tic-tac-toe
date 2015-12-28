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
            playOneRound(mark, board);
            mark = board.switchMark(mark);
        }
        finishGame(mark, board, rules);
        if (userInterface.playAgain()) {
            play();
        }
    }

    public Integer usersPosition(Board board) {
        Integer position = userInterface.playersChosenPosition(board);
        return validPosition(position, board);
    }

    private void playOneRound(Cell mark, Board board) {
        userInterface.displayBoard(board.cells());
        board.placeMark(usersPosition(board), mark);
    }

    private void finishGame(Cell mark, Board board, Rules rules) {
        userInterface.displayBoard(board.cells());
        userInterface.announceGameEnd(board.switchMark(mark), rules.hasWinner());
    }

    private Integer validPosition(Integer position, Board board) {
        if (board.isPositionAvailable(position)) {
            return position;
        } else {
            return askUserAgainForPosition(board);
        }
    }

    private Integer askUserAgainForPosition(Board board) {
        userInterface.positionUnavailableWarning();
        return usersPosition(board);
    }
}
