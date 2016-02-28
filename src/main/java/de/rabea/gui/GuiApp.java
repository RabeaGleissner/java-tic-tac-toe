package de.rabea.gui;

import de.rabea.game.*;
import de.rabea.player.PlayerFactory;

import static de.rabea.game.GameMode.GuiHumanVsGuiHuman;

public class GuiApp {

    private final ViewUpdater viewUpdater;
    private final PlayerFactory playerFactory;
    private Game game;


    public GuiApp(ViewUpdater viewUpdater, PlayerFactory playerFactory) {
        this.viewUpdater = viewUpdater;
        this.playerFactory = playerFactory;
    }

    public void displayGameOptions() {
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
        Player player = playerFactory.createPlayer(GuiHumanVsGuiHuman);
        game = new Game(new JavaFXUi(viewUpdater, this), player,
                playerFactory.createOpponent(GuiHumanVsGuiHuman));
        playOneRound(board);
    }

    public void playOneRound(Board board) {
        game.play(board);
    }
}

