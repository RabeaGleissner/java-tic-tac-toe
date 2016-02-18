package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiAppTest {

    @Test
    public void createsGame() {
        ViewUpdater viewUpdater = new ViewUpdater(new Scene(new GridPane()));
        GuiApp guiApp = new GuiApp(viewUpdater);
        assertTrue(guiApp.createGame(new Board(3), new GuiPlayer()) instanceof GuiGame);
    }

}