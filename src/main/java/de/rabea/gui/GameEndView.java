package de.rabea.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameEndView {

    public GridPane draw(GuiApp guiApp) {
        GridPane gridPane = new GridPane();
        gridPane.add(new Text("Game over"), 3, 3);
        JavaFXButton replayButton = new JavaFXButton();
        replayButton.setText("Click to play again");
        replayButton.setOnAction(new ReplayClickHandler(guiApp));
        gridPane.add(replayButton.getActualButton(), 4, 5);
        return gridPane;
    }
}
