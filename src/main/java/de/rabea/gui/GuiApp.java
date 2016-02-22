package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.Mark;

public class GuiApp {

    private ViewUpdater viewUpdater;

    public GuiApp(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void displayGameOptions() {
        viewUpdater.showBoardSizeOptionsView(this);
    }

    public void displayBoard() {
        Board board = new Board(3);
        ClickCarrier carrier = new ClickCarrier();
        NextGuiPlayer guiPlayer = new NextGuiPlayer(Mark.X, carrier);
        NextGuiPlayer guiOpponent = new NextGuiPlayer(Mark.O, carrier);
        viewUpdater.showBoard(guiPlayer, board);
        start(guiPlayer, guiOpponent, viewUpdater);
    }

    public void start(NextGuiPlayer guiPlayer, NextGuiPlayer guiOpponent, ViewUpdater viewUpdater) {
        Game game = new Game(new JavaFXUi(guiPlayer, viewUpdater, this), guiPlayer, guiOpponent);
        Thread thread = new Thread(){
            public void run(){
                game.play(3);
            }
        };

        thread.start();
    }

}
