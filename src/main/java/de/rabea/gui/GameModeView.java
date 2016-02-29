package de.rabea.gui;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.geometry.Pos.CENTER;

public class GameModeView {

    public Parent draw(GuiApp guiApp) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(CENTER);
        Label label = new Label("Please select a game mode:");
        label.getStyleClass().add("header");
        gridPane.add(label, 0, 0, 3, 1);
        addButtons(guiApp, gridPane);
        return gridPane;
    }

    private void addButtons(GuiApp guiApp, GridPane gridPane) {
        gridPane.add(createButton(guiApp, "Human vs Human").getActualButton(), 1, 1);
        gridPane.add(createButton(guiApp, "Human vs Computer").getActualButton(), 1, 2);
    }

    private JavaFXButton createButton(GuiApp guiApp, String gameMode) {
        return new JavaFXButton(new GameOptionsClickHandler(guiApp), gameMode,
                idWithoutWhiteSpace(gameMode), "game-mode-buttons");
    }

    private String idWithoutWhiteSpace(String gameMode) {
        return gameMode.replaceAll("\\s","");
    }
}
