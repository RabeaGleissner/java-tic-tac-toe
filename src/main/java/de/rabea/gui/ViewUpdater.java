package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;

public class ViewUpdater {

    private Scene scene;

    public ViewUpdater(Scene scene) {
        this.scene = scene;
    }

    public void showBoard(GuiPlayer guiPlayer, Board board) {

        // FIXME: Will need to pass the 'ClickCarrier' in
        BoardView boardView = new BoardView(board, new BoardClickHandler(new ClickCarrier()));
        scene.setRoot(boardView.draw());

        throw new RuntimeException("Hi there! I am being called");
    }

    public void showGameOverView() {
        GameEndView gameEndView = new GameEndView();
        scene.setRoot(gameEndView.draw());
    }
}
