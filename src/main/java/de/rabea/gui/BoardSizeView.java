package de.rabea.gui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BoardSizeView {

    public Parent draw(GuiApp guiApp) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(new Label("Please select a board size:"), 0, 0);
        JavaFXButton button3x3 = createBoardSizeButton(guiApp);
        gridPane.add(button3x3.getActualButton(), 1, 1);
        return gridPane;
    }

    private JavaFXButton createBoardSizeButton(GuiApp guiApp) {
        JavaFXButton button3x3 = new JavaFXButton();
        button3x3.setOnAction(new BoardSizeClickHandler(guiApp));
        button3x3.setText("3x3 board");
        return button3x3;
    }
}
