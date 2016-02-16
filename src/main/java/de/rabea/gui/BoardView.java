package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
        int i = 0;
        for (Mark cell : board.cells()) {
            int column = i / 3;
            int row= i % 3;
            if (cell == EMPTY) {
                Button button = new Button(cell.toString());
                button.setOnAction( event -> carrier.click(7));
                gridPane.add(button, column, row);
            } else {
                gridPane.add(new Label(cell.toString()), column, row);
            }
            i++;
        }

        return gridPane;
    }
}
