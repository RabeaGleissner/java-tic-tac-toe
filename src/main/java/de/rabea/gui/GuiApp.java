package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.GameMode;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;

public class GuiApp {

    private final UserInterface userInterface;
    private final PlayerFactory playerFactory;
    private Game game;


    public GuiApp(UserInterface userInterface, PlayerFactory playerFactory) {
        this.userInterface = userInterface;
        this.playerFactory = playerFactory;
    }

    public void displayGameModeOptions() {
        userInterface.getGameModeFromUser();
    }

    public void createGameAndGetBoardSize(GameMode gameMode) {
        game = createNewGame(gameMode);
        userInterface.getBoardDimensionFromUser();
    }

    public Game createNewGame(GameMode gameMode) {
        return new Game(userInterface, playerFactory.createPlayer(gameMode),
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

