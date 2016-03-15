package de.rabea.gui.view;

import de.rabea.game.GameFactory;
import de.rabea.gui.JavaFXButton;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.clickhandler.GameModeClickHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.geometry.Pos.CENTER;

public class GameModeView {

    public Parent draw(JavaFXUi javaFXUi, GameFactory gameFactory) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(CENTER);
        Label label = new Label("Please select a game mode:");
        label.getStyleClass().add("header");
        gridPane.add(label, 0, 0, 3, 1);
        addButtons(javaFXUi, gameFactory, gridPane);
        return gridPane;
    }

    private void addButtons(JavaFXUi javaFXUi, GameFactory gameFactory, GridPane gridPane) {
        gridPane.add(createButton(javaFXUi, gameFactory, "Human vs Human").getActualButton(), 1, 1);
        gridPane.add(createButton(javaFXUi, gameFactory, "Human vs Computer").getActualButton(), 1, 2);
    }

    private JavaFXButton createButton(JavaFXUi javaFXUi, GameFactory gameFactory, String gameMode) {
        return new JavaFXButton(new GameModeClickHandler(gameFactory), gameMode,
                idWithoutWhiteSpace(gameMode), "game-mode-buttons");
    }

    private String idWithoutWhiteSpace(String gameMode) {
        return gameMode.replaceAll("\\s","");
    }
}
