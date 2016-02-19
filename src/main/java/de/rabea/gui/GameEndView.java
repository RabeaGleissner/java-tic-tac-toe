package de.rabea.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameEndView implements GuiView {

    public GridPane draw() {
        GridPane gridPane = new GridPane();
        gridPane.add(new Text("Game over"), 3, 3);
        JavaFXButton replayButton = new JavaFXButton();
        replayButton.setText("Click to play again");
        gridPane.add(replayButton.getActualButton(), 4, 5);
        return gridPane;
    }
}
