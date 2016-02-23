package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.Mark;

public class GuiApp {

    private ViewUpdater viewUpdater;
    private Board board = null;
    private Game game;

    public GuiApp(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void displayGameOptions() {
        viewUpdater.showBoardSizeOptionsView(this);
    }

    public void createBoard(String boardSize) {
        if (boardSize.equals("3x3")) {
            board = new Board(3);
        } else {
            board = new Board(4);
        }
        prepareGameForPlaying();
    }

    public void prepareGameForPlaying() {
        ClickCarrier carrier = new ClickCarrier();
        GuiPlayer guiPlayer = new GuiPlayer(Mark.X, carrier);
        GuiPlayer guiOpponent = new GuiPlayer(Mark.O, carrier);
        viewUpdater.showBoard(guiPlayer, board, this);
        game = new Game(new JavaFXUi(guiPlayer, viewUpdater, this), guiPlayer, guiOpponent);
        startGame();
    }

    public void startGame() {
        game.play(board);
    }

    public Board getBoard() {
        return board;
    }
}

