package de.rabea.game;

import de.rabea.gui.GuiPlayer;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

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

    public Game createGame(GameMode gameMode) {
        return new Game(userInterface, playerFactory.createPlayer(gameMode),
                playerFactory.createOpponent(gameMode));
    }

    public Board createBoard(int boardSize) {
        return new Board(boardSize);
    }

    public void createBoardAndPlay(int boardSize) {
//        if (!(game.getPlayer() instanceof GuiPlayer) && !(game.getOpponent() instanceof GuiPlayer) ) {
            playOneRound(createBoard(boardSize));
//        }
    }

    public void playOneRound(Board board) {
        game.play(board);
    }

    public void setUpConsoleGameAndPlay() {
        displayGameModeOptions();
        setGameAndDisplayBoardSizeOptions(gameMode);
        createBoardAndPlay(boardSize);
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
}
