package de.rabea.gui;

import de.rabea.game.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Board board = new Board(3);
        BoardView boardView = new BoardView(board, new ClickCarrier());
        primaryStage.setScene(new Scene(boardView.draw()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
