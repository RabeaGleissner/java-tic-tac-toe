package de.rabea.gui;

import de.rabea.game.Board;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GuiAppTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void startsGameAndShowsBoard() {
        ViewUpdaterSpy viewUpdaterSpy = new ViewUpdaterSpy(new Scene(new GridPane()));
        GuiApp guiApp = new GuiApp(viewUpdaterSpy);
        guiApp.displayBoard();
        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

    private class ViewUpdaterSpy extends ViewUpdater {
        private boolean hasShownBoard = false;

        public ViewUpdaterSpy(Scene scene) {
            super(scene);
        }

        @Override
        public void showBoard(NextGuiPlayer guiPlayer, Board board) {
            hasShownBoard = true;
        }
    }
}