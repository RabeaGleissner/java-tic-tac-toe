package de.rabea.gui;

import javafx.scene.control.Button;

public class JavaFXButton {

    private final Button actualButton;

    public JavaFXButton() {
        this.actualButton = new Button();
    }

    public void setOnAction(ClickHandler clickHandler) {
        actualButton.setOnAction(event -> clickHandler.action(actualButton.getId()));
    }

    public Button getActualButton() {
        return actualButton;
    }

    public void setId(String id) {
        actualButton.setId(id);
    }

    public void setText(String text) {
        actualButton.setText(text);
    }
}
