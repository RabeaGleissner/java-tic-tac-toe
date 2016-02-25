package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Map;

import static de.rabea.game.Mark.EMPTY;
import static javafx.geometry.Pos.CENTER;

public class BoardView {

    private final ClickHandler boardClickHandler;
    private final ClickHandler positionInUseClickHandler;

    public BoardView(ClickHandler boardClickHandler, ClickHandler positionInUseClickHandler) {
        this.boardClickHandler = boardClickHandler;
        this.positionInUseClickHandler = positionInUseClickHandler;
    }

    public Parent draw(Board board, boolean positionInUse) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(CENTER);
        for (Map.Entry<Integer, Mark> entry : board.cellsWithIndex().entrySet()) {
            int position = entry.getKey();
            Mark cell = entry.getValue();
            int column = position % board.getDimension();
            int row = position / board.getDimension();
            if (cell == EMPTY) {
                JavaFXButton activeButton = new JavaFXButton(boardClickHandler, "_", position + "", "active-button");
                gridPane.add(activeButton.getActualButton(), column, row);
            } else {
                JavaFXButton disabledButton = new JavaFXButton(positionInUseClickHandler, cell.toString(), cell.toString(), "disabled-button");
                gridPane.add(disabledButton.getActualButton(), column, row);
            }
        }
        if (positionInUse) {
            gridPane.add(new Text("Position already in use!"), 0, 5, 3, 1);
        }

        return gridPane;
    }
}
