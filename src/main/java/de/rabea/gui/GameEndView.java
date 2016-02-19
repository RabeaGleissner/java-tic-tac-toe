package de.rabea.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameEndView {

    public GridPane draw() {
        GridPane gridPane = new GridPane();
        gridPane.add(new Text("Game over"), 3, 3);
        Button replayButton = new Button("Click to play again");
        gridPane.add(replayButton, 4, 5);
        return gridPane;
    }
}
