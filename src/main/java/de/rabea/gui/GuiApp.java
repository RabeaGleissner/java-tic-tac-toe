package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.GameMode;
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

    public void createGameAndGetBoardSize(GameMode gameMode) {
        game = createNewGame(gameMode);
        viewUpdater.showBoardSizeOptionsView(this);
    }

    public Game createNewGame(GameMode gameMode) {
        return new Game(new JavaFXUi(viewUpdater, this), playerFactory.createPlayer(gameMode),
                playerFactory.createOpponent(gameMode));
    }

    public Board createBoard(String boardSize) {
        if (boardSize.equals("3x3")) {
            return new Board(3);
        } else {
            return new Board(4);
        }
    }

    public void startGame(String boardSize) {
        Board board = createBoard(boardSize);
        playOneRound(board);
    }

    public void playOneRound(Board board) {
        game.play(board);
    }
}

