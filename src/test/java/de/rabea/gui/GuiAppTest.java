package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiAppTest {

    @Test
    public void startsGameAndShowsBoard() {
        ViewUpdaterSpy viewUpdaterSpy = new ViewUpdaterSpy(new Scene(new GridPane()));
        GuiApp guiApp = new GuiApp(viewUpdaterSpy);
        guiApp.start();
        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

    @Ignore
    @Test
    public void createsGame() {
        ViewUpdater viewUpdater = new ViewUpdater(new Scene(new GridPane()));
        GuiApp guiApp = new GuiApp(viewUpdater);
    }

    private class ViewUpdaterSpy extends ViewUpdater {
        private boolean hasShownBoard = false;

        public ViewUpdaterSpy(Scene scene) {
            super(scene);
        }

//        @Override
//        public void showBoard(ClickCarrier carrier, Board board) {
//            hasShownBoard = true;
//        }
    }
}