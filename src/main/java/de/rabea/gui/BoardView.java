package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Map;

import static de.rabea.game.Mark.EMPTY;

public class BoardView {

    private final ClickHandler boardClickHandler;

    public BoardView(ClickHandler boardClickHandler) {
        this.boardClickHandler = boardClickHandler;
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
                JavaFXButton activeButton = new JavaFXButton(boardClickHandler, "_", position + "", "active-button");
                gridPane.add(activeButton.getActualButton(), column, row);
            } else {
                JavaFXButton disabledButton = new JavaFXButton(boardClickHandler, cell.toString(), cell.toString(), "disabled-button");
                gridPane.add(disabledButton.getActualButton(), column, row);
            }
        }
        return gridPane;
    }
}
