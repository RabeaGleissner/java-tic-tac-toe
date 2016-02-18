package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiPlayerTest {

    @Test
    public void playsOneRoundOnClick() {
        Board board = new Board(3);
        GuiPlayer guiPlayer = new GuiPlayer(new GuiGame(board, new ViewUpdater(new Scene(new GridPane()))));
        guiPlayer.click(1);
        assertFalse(board.isPositionAvailable(1));
    }

}