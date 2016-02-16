package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardView {

    private Board board;

    public BoardView(Board board) {
        this.board = board;
    }

    public Parent draw() {
        GridPane gridPane = new GridPane();
        int i = 0;
        for (Mark cell : board.cells()) {
            gridPane.add(new Button(cell.toString()), i / 3, i % 3);
            i++;
        }


        return gridPane;
    }
}
