package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Map;

import static de.rabea.game.Mark.*;

public class BoardView {

    private Board board;
    private ClickCarrier carrier;

    public BoardView(Board board, ClickCarrier carrier) {
        this.board = board;
        this.carrier = carrier;
    }

    public Parent draw() {
        GridPane gridPane = new GridPane();
        for (Map.Entry<Integer, Mark> entry: board.marks().entrySet()) {
            int column = entry.getKey() / 3;
            int row= entry.getKey() % 3;
            if (entry.getValue() == EMPTY) {
                Button button = new Button(entry.getValue().toString());
                button.setOnAction( event -> carrier.click(entry.getKey()));
                gridPane.add(button, column, row);
            } else {
                gridPane.add(new Label(entry.getValue().toString()), column, row);
            }
        }

        return gridPane;
    }
}
