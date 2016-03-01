package de.rabea.gui.view;

import de.rabea.game.GameRunner;
import de.rabea.gui.JavaFXButton;
import de.rabea.gui.clickhandler.BoardSizeClickHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.geometry.Pos.CENTER;

public class BoardSizeView {

    public Parent draw(GameRunner gameRunner) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(CENTER);
        Label label = new Label("Please select a board size:");
        label.getStyleClass().add("header");
        gridPane.add(label, 0, 0, 3, 1);
        addButtons(gameRunner, gridPane);
        return gridPane;
    }

    private void addButtons(GameRunner gameRunner, GridPane gridPane) {
        gridPane.add(createButton(gameRunner, "3x3").getActualButton(), 1, 1);
        gridPane.add(createButton(gameRunner, "4x4").getActualButton(), 2, 1);
    }

    private JavaFXButton createButton(GameRunner gameRunner, String size) {
        return new JavaFXButton(new BoardSizeClickHandler(gameRunner), size + " board",
                size, "board-size-buttons");
    }
}
