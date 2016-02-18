package de.rabea.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameOverView {

    public GridPane draw() {
        GridPane gridPane = new GridPane();
        gridPane.add(new Text("Game over"), 3, 3);
        return gridPane;
    }
}
