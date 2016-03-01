package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.GameRunner;
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

    public void showGameModeOptions(GameRunner gameRunner) {
        scene.setRoot(new GameModeView().draw(gameRunner));
    }

    public void showBoardSizeOptionsView(GameRunner gameRunner) {
        scene.setRoot(new BoardSizeView().draw(gameRunner));
    }

    public void showBoard(GuiPlayer guiPlayer, Board board, GameRunner gameRunner, boolean positionFull) {
        BoardView boardView = new BoardView(new EmptyCellClickHandler(guiPlayer, gameRunner, board),
                new FullCellClickHandler(this, guiPlayer, board, gameRunner));
        scene.setRoot(boardView.draw(board, positionFull));
    }

    public void showGameEndView(GameRunner gameRunner, Mark lastPlayedMark, boolean winner) {
        scene.setRoot(new GameEndView().draw(gameRunner, lastPlayedMark, winner));
    }
}
