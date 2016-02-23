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
        JavaFXButton button3x3 = create3x3Button(guiApp);
        JavaFXButton button4x4 = create4x4Button(guiApp);
        gridPane.add(button3x3.getActualButton(), 1, 1);
        gridPane.add(button4x4.getActualButton(), 2, 1);
        return gridPane;
    }

    private JavaFXButton create4x4Button(GuiApp guiApp) {
        JavaFXButton button4x4 = new JavaFXButton();
        button4x4.setOnAction(new BoardSizeClickHandler(guiApp));
        button4x4.setText("4x4 board");
        button4x4.setId("4x4");
        return button4x4;
    }

    private JavaFXButton create3x3Button(GuiApp guiApp) {
        JavaFXButton button3x3 = new JavaFXButton();
        button3x3.setOnAction(new BoardSizeClickHandler(guiApp));
        button3x3.setText("3x3 board");
        button3x3.setId("3x3");
        return button3x3;
    }
}
