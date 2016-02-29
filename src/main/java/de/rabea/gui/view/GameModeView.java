package de.rabea.gui.view;

import de.rabea.game.GameRunner;
import de.rabea.gui.JavaFXButton;
import de.rabea.gui.clickhandler.GameModeClickHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.geometry.Pos.CENTER;

public class GameModeView {

    public Parent draw(GameRunner gameRunner) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(CENTER);
        Label label = new Label("Please select a game mode:");
        label.getStyleClass().add("header");
        gridPane.add(label, 0, 0, 3, 1);
        addButtons(gameRunner, gridPane);
        return gridPane;
    }

    private void addButtons(GameRunner gameRunner, GridPane gridPane) {
        gridPane.add(createButton(gameRunner, "Human vs Human").getActualButton(), 1, 1);
        gridPane.add(createButton(gameRunner, "Human vs Computer").getActualButton(), 1, 2);
    }

    private JavaFXButton createButton(GameRunner gameRunner, String gameMode) {
        return new JavaFXButton(new GameModeClickHandler(gameRunner), gameMode,
                idWithoutWhiteSpace(gameMode), "game-mode-buttons");
    }

    private String idWithoutWhiteSpace(String gameMode) {
        return gameMode.replaceAll("\\s","");
    }
}
