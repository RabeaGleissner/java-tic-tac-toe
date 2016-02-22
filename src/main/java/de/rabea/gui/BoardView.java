package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Map;

import static de.rabea.game.Mark.*;

public class BoardView {

    private ClickHandler clickHandler;

    public BoardView(ClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public Parent draw(Board board) {
        GridPane gridPane = new GridPane();
        for (Map.Entry<Integer, Mark> entry : board.cellsWithIndex().entrySet()) {
            int position = entry.getKey();
            Mark cell = entry.getValue();
            int column = position % 3;
            int row = position / 3;
            if (cell == EMPTY) {
                JavaFXButton javaFXButton = createButton(position);
                gridPane.add(javaFXButton.getActualButton(), column, row);
            } else {
                gridPane.add(new Label(cell.toString()), column, row);
            }
        }
        return gridPane;
    }

    private JavaFXButton createButton(int position) {
        JavaFXButton javaFXButton = new JavaFXButton();
        javaFXButton.setOnAction(clickHandler);
        javaFXButton.setText((position + 1) + "");
        javaFXButton.setId(position);
        return javaFXButton;
    }
}
