package de.rabea.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameEndView {

    public GridPane draw(GuiApp guiApp) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Label label = new Label("Game over");
        label.getStyleClass().add("header");
        gridPane.add(label, 0, 0, 3, 1);
        JavaFXButton replayButton = new JavaFXButton(new ReplayClickHandler(guiApp), "Click to play again", "replayButton", "replay-button");
        gridPane.add(replayButton.getActualButton(), 1, 1, 3, 1);
        return gridPane;
    }
}
