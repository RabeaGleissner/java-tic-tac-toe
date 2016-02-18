package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;

public class ViewUpdater {

    private Scene scene;

    public ViewUpdater(Scene scene) {
        this.scene = scene;
    }

    public void showBoard(GuiPlayer guiPlayer, Board board) {
        BoardView boardView = new BoardView(board, guiPlayer);
        scene.setRoot(boardView.draw());
    }

    public void showGameOverView() {
        GameOverView gameOverView = new GameOverView();
        scene.setRoot(gameOverView.draw());
    }
}
