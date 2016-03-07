package de.rabea.game;

import de.rabea.player.PlayerFactory;

public class GameRunner {
    private final UserInterface userInterface;
    private final PlayerFactory playerFactory;
    private GameMode gameMode;
    private Game game;
    private int boardSize;

    public GameRunner(UserInterface userInterface, PlayerFactory playerFactory) {
        this.userInterface = userInterface;
        this.playerFactory = playerFactory;
    }

    public void displayGameModeOptions() {
       gameMode = userInterface.getGameModeFromUser();
    }

    public void setGameAndDisplayBoardSizeOptions(GameMode gameMode) {
        game = createGame(gameMode);
        boardSize = userInterface.getBoardDimensionFromUser();
    }

    public void playWithFreshBoard(int boardSize) {
        playOneRound(createBoard(boardSize));
    }

    public void playOneRound(Board board) {
        game.play(board);
    }

    public void setUpConsoleGameAndPlay() {
        displayGameModeOptions();
        setGameAndDisplayBoardSizeOptions(gameMode);
        playWithFreshBoard(boardSize);
        replayIfRequested();
    }

    private void replay() {
        setUpConsoleGameAndPlay();
    }

    private void replayIfRequested() {
        if (userInterface.playAgain()) {
            replay();
        }
    }

    public Game createGame(GameMode gameMode) {
        return new Game(userInterface, playerFactory.createPlayer1(gameMode),
                playerFactory.createPlayer2(gameMode));
    }

    public Board createBoard(int boardSize) {
        return new Board(boardSize);
    }
}
