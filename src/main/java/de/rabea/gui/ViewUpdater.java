package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.scene.Scene;

public class ViewUpdater {

    private final Scene scene;

    public ViewUpdater(Scene scene) {
        this.scene = scene;
    }

    public void showBoardSizeOptionsView(GuiApp guiApp) {
        BoardSizeView boardSizeView = new BoardSizeView();
        scene.setRoot(boardSizeView.draw(guiApp));
    }

    public void showBoard(GuiPlayer guiPlayer, Board board, GuiApp guiApp, boolean positionFull) {
        BoardView boardView = new BoardView(new EmptyCellClickHandler(guiPlayer, guiApp, board), new FullCellClickHandler(this, guiPlayer, board, guiApp));
        scene.setRoot(boardView.draw(board, positionFull));
    }

    public void showGameOverView(GuiApp guiApp, Mark lastPlayedMark, boolean winner) {
        GameEndView gameEndView = new GameEndView();
        scene.setRoot(gameEndView.draw(guiApp, lastPlayedMark, winner));
    }
}
