package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void play() {
        userInterface.greet();
        GameMode gameMode = userInterface.chooseGameMode();

        Cell mark = Cell.X;
        Board board = new Board();
        Rules rules = new Rules(board);

        while (gameIsNotOver(rules)){
            playOneHumanRound(mark, board);
            mark = board.switchMark(mark);

            if (humanVsComputerMode(gameMode) && gameIsNotOver(rules)) {
                playOneComputerRound(board, mark, rules);
                mark = board.switchMark(mark);
            }
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

    public void playOneComputerRound(Board board, Cell mark, Rules rules) {
        ComputerPlayer computerPlayer = new ComputerPlayer(new RandomNumberCalc(), board);
        board.placeMark(computerPlayer.getPosition(), mark);
    }


    private void playOneHumanRound(Cell mark, Board board) {
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

    private boolean humanVsComputerMode(GameMode gameMode) {
        return gameMode == GameMode.HvC;
    }

    private boolean gameIsNotOver(Rules rules) {
       return !rules.gameOver();
    }
}
