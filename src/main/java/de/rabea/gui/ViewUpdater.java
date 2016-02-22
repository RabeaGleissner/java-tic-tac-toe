package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;

public class ViewUpdater {

    private Scene scene;

    public ViewUpdater(Scene scene) {
        this.scene = scene;
    }

    public void showBoard(ClickCarrier carrier, Board board) {
        BoardView boardView = new BoardView(new BoardClickHandler(carrier));
        scene.setRoot(boardView.draw(board));
    }

    public void showGameOverView() {
        GameEndView gameEndView = new GameEndView();
        scene.setRoot(gameEndView.draw());
    }
}
