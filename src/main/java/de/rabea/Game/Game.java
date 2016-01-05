package de.rabea.game;

import de.rabea.ui.UserInterface;

public class Game {

    private final UserInterface userInterface;
    private final ComputerPlayer computerPlayer;

    public Game(UserInterface userInterface, ComputerPlayer computerPlayer) {
        this.userInterface = userInterface;
        this.computerPlayer = computerPlayer;
    }

    public void play() {
        GameMode game = gameSetup();
        Cell mark = Cell.X;
        Board board = new Board();
        Rules rules = new Rules(board);
        while (gameIsNotOver(rules)){
            playOneHumanRound(mark, board);
            mark = board.switchMark(mark);
            if (humanVsComputerMode(game) && gameIsNotOver(rules)) {
                playOneComputerRound(board, mark);
                mark = board.switchMark(mark);
            }
        }
        finishGame(mark, board, rules);
        if (userInterface.playAgain()) {
            play();
        }
    }

    private GameMode gameSetup() {
        userInterface.greet();
        GameMode gameMode = userInterface.chooseGameMode();
        userInterface.announceMarkDistribution(gameMode);
        return gameMode;
    }

    public Integer usersPosition(Board board) {
        Integer position = userInterface.playersChosenPosition(board);
        return validPosition(position, board);
    }

    private void playOneComputerRound(Board board, Cell mark) {
        board.placeMark(computerPlayer.getPosition(board), mark);
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
