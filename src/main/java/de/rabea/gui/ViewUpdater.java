package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.gui.clickhandler.EmptyCellClickHandler;
import de.rabea.gui.clickhandler.FullCellClickHandler;
import de.rabea.gui.view.BoardSizeView;
import de.rabea.gui.view.BoardView;
import de.rabea.gui.view.GameEndView;
import de.rabea.gui.view.GameModeView;
import javafx.scene.Scene;

public class ViewUpdater {

    private final Scene scene;

    public ViewUpdater(Scene scene) {
        this.scene = scene;
    }

    public void showGameModeOptions(GuiApp guiApp) {
        scene.setRoot(new GameModeView().draw(guiApp));
    }

    public void showBoardSizeOptionsView(GuiApp guiApp) {
        scene.setRoot(new BoardSizeView().draw(guiApp));
    }

    public void showBoard(GuiPlayer guiPlayer, Board board, GuiApp guiApp, boolean positionFull) {
        BoardView boardView = new BoardView(new EmptyCellClickHandler(guiPlayer, guiApp, board),
                new FullCellClickHandler(this, guiPlayer, board, guiApp));
        scene.setRoot(boardView.draw(board, positionFull));
    }

    public void showGameEndView(GuiApp guiApp, Mark lastPlayedMark, boolean winner) {
        scene.setRoot(new GameEndView().draw(guiApp, lastPlayedMark, winner));
    }

}
