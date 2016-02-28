package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.GameMode;
import de.rabea.game.Player;
import de.rabea.player.PlayerFactory;

public class GuiApp {

    private final ViewUpdater viewUpdater;
    private final PlayerFactory playerFactory;
    private Game game;


    public GuiApp(ViewUpdater viewUpdater, PlayerFactory playerFactory) {
        this.viewUpdater = viewUpdater;
        this.playerFactory = playerFactory;
    }

    public void displayGameModeOptions() {
        viewUpdater.showGameModeOptions(this);
    }

    public void createGame(GameMode gameMode) {
        Player player = playerFactory.createPlayer(gameMode);
        game = new Game(new JavaFXUi(viewUpdater, this), player,
                playerFactory.createOpponent(gameMode));
        displayBoardSizeOptions();
    }

    public void displayBoardSizeOptions() {
        viewUpdater.showBoardSizeOptionsView(this);
    }

    public Board createBoard(String boardSize) {
        if (boardSize.equals("3x3")) {
            return new Board(3);
        } else {
            return new Board(4);
        }
    }

    public void prepareGameForPlaying(String boardSize) {
        Board board = createBoard(boardSize);
        playOneRound(board);
    }

    public void playOneRound(Board board) {
        game.play(board);
    }
}

