package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.geometry.Pos;
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
        gridPane.setAlignment(Pos.CENTER);
        for (Map.Entry<Integer, Mark> entry : board.cellsWithIndex().entrySet()) {
            int position = entry.getKey();
            Mark cell = entry.getValue();
            int column = position % board.getDimension();
            int row = position / board.getDimension();
            if (cell == EMPTY) {
                JavaFXButton javaFXButton = createButton(position);
                gridPane.add(javaFXButton.getActualButton(), column, row);
            } else {
                Label label = new Label(cell.toString());
                label.getStyleClass().add("cell");
                gridPane.add(label, column, row);
            }
        }
        return gridPane;
    }

    private JavaFXButton createButton(int position) {
        JavaFXButton javaFXButton = new JavaFXButton(clickHandler, (position + 1) + "", position + "", "cell");
        return javaFXButton;
    }
}
