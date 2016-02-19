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

    public void setId(int position) {
        actualButton.setId(position + "");
    }
}
