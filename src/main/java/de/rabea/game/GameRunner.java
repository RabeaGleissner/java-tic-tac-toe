package de.rabea.game;

public class GameRunner {
    private final UserInterface userInterface;
    private GameFactory gameFactory;

    private GameMode gameMode;
    private Game game;
    private int boardSize;

    public GameRunner(UserInterface userInterface, GameFactory gameFactory) {
        this.userInterface = userInterface;
        this.gameFactory = gameFactory;
    }

    public void displayGameModeOptions() {
        gameMode = userInterface.getGameModeFromUser(gameFactory);
    }

    public void setupGame(GameMode gameMode) {
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
        setupGame(gameMode);
        playWithFreshBoard(boardSize);
        replayIfRequested();
    }

    public Game createGame(GameMode gameMode) {
        return gameFactory.createGame(gameMode);
    }

    Board createBoard(int boardSize) {
        return new Board(boardSize);
    }

    private void replayIfRequested() {
        if (userInterface.playAgain()) {
            replay();
        }
    }

    private void replay() {
        setUpConsoleGameAndPlay();
    }
}
