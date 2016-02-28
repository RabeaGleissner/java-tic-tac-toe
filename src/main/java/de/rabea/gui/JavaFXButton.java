package de.rabea.gui;

import javafx.scene.control.Button;

public class JavaFXButton {

    private final Button actualButton;

    public JavaFXButton(ClickHandler clickHandler, String text, String id, String cssClass) {
        this.actualButton = new Button();
        actualButton.setId(id);
        actualButton.setOnAction(event -> clickHandler.action(actualButton.getId()));
        actualButton.setText(text);
        actualButton.getStyleClass().add(cssClass);
    }

    public Button getActualButton() {
        return actualButton;
    }
}
