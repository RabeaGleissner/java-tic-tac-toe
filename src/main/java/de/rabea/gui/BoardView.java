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
    private GuiPlayer guiPlayer;

    public BoardView(Board board, GuiPlayer guiPlayer) {
        this.board = board;
        this.guiPlayer = guiPlayer;
    }

    public Parent draw() {
        GridPane gridPane = new GridPane();
        for (Map.Entry<Integer, Mark> entry : board.cellsWithIndex().entrySet()) {
            int position = entry.getKey();
            Mark cell = entry.getValue();
            int column = position / 3;
            int row = position % 3;
            if (cell == EMPTY) {
                Button button = new Button(position + "");
                button.setOnAction(event -> guiPlayer.click(position));
                gridPane.add(button, column, row);
            } else {
                gridPane.add(new Label(cell.toString()), column, row);
            }
        }
        return gridPane;
    }
}
