package de.rabea.gui.view;

import de.rabea.game.Mark;
import de.rabea.gui.GuiApp;
import de.rabea.gui.JavaFXButton;
import de.rabea.gui.clickhandler.ReplayClickHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.geometry.Pos.CENTER;

public class GameEndView {

    public GridPane draw(GuiApp guiApp, Mark lastPlayedMark, boolean winner) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(CENTER);
        Label winnerMessage = new Label(message(lastPlayedMark, winner));
        winnerMessage.getStyleClass().add("header");
        gridPane.add(winnerMessage, 1, 1, 3, 1);
        JavaFXButton replayButton = new JavaFXButton(new ReplayClickHandler(guiApp),
                "Play again", "replayButton", "replay-button");
        gridPane.add(replayButton.getActualButton(), 2, 2, 3, 1);
        return gridPane;
    }

    private String message(Mark lastPlayedMark, boolean winner) {
        if (winner) {
            return "Game over. Winner is " + lastPlayedMark.toString() + ".";
        } else {
            return "Game over. It's a draw!";
        }
    }
}
