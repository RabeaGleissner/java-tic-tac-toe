package de.rabea.gui;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.geometry.Pos.CENTER;

public class BoardSizeView {

    public Parent draw(GuiApp guiApp) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(CENTER);
        Label label = new Label("Please select a board size:");
        label.getStyleClass().add("header");
        gridPane.add(label, 0, 0, 3, 1);
        addButtons(guiApp, gridPane);
        return gridPane;
    }

    private void addButtons(GuiApp guiApp, GridPane gridPane) {
        gridPane.add(createButton(guiApp, "3x3").getActualButton(), 1, 1);
        gridPane.add(createButton(guiApp, "4x4").getActualButton(), 2, 1);
    }

    private JavaFXButton createButton(GuiApp guiApp, String size) {
        return new JavaFXButton(new BoardSizeClickHandler(guiApp), size + " board", size, "board-size-buttons");
    }
}
