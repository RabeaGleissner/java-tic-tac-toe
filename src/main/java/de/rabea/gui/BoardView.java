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

    public BoardView(Board board) {
        this.board = board;
    }

    public Parent draw() {
        GridPane gridPane = new GridPane();
        int i = 0;
        for (Mark cell : board.cells()) {
            int column = i / 3;
            int row= i % 3;
            if (cell == EMPTY) {
                gridPane.add(new Button(cell.toString()), column, row);
            } else {
                gridPane.add(new Label("hi"), column, row);
            }
            i++;
        }


        return gridPane;
    }
}
