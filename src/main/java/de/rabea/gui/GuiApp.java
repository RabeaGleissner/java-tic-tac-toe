package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.Mark;

public class GuiApp {

    private final ViewUpdater viewUpdater;
    private Game game;

    public GuiApp(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
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
        GuiPlayer player = new GuiPlayer(Mark.X);
        GuiPlayer opponent = new GuiPlayer(Mark.O);
        viewUpdater.showBoard(player, board, this, false);
        game = new Game(new JavaFXUi(viewUpdater, this), player, opponent);
        playOneRound(board, player);
    }

    public void playOneRound(Board board, GuiPlayer player) {
        viewUpdater.showBoard(player, board, this, false);
        game.play(board);
    }
}

