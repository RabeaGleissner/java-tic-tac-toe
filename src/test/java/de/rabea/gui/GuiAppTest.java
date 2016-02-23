package de.rabea.gui;

import de.rabea.game.Board;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GuiAppTest {
    ViewUpdaterSpy viewUpdaterSpy;

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
        viewUpdaterSpy = new ViewUpdaterSpy(new Scene(new GridPane()));
    }

    @Test
    public void displaysGameOptions() {
        GuiApp guiApp = new GuiApp(viewUpdaterSpy);
        guiApp.displayGameOptions();
        assertTrue(viewUpdaterSpy.hasShownBoardSizeOptions);
    }

    @Test
    public void creates3x3Board() {
        GuiApp guiApp = new GuiApp(new ViewUpdater(new Scene(new GridPane())));
        guiApp.createBoard("3x3");
        assertEquals(3, guiApp.getBoard().getDimension());
    }

    @Test
    public void creates4x4Board() {
        GuiApp guiApp = new GuiApp(new ViewUpdater(new Scene(new GridPane())));
        guiApp.createBoard("4x4");
        assertEquals(4, guiApp.getBoard().getDimension());
    }

    @Test
    public void preparesGameAndShowsBoard() {
        GuiApp guiApp = new GuiApp(viewUpdaterSpy);
        guiApp.createBoard("3x3");
        guiApp.prepareGameForPlaying();
        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

    private class ViewUpdaterSpy extends ViewUpdater {
        private boolean hasShownBoard = false;
        private boolean hasShownBoardSizeOptions = false;

        public ViewUpdaterSpy(Scene scene) {
            super(scene);
        }

        @Override
        public void showBoard(GuiPlayer guiPlayer, Board board, GuiApp guiApp) {
            hasShownBoard = true;
        }

        @Override
        public void showBoardSizeOptionsView(GuiApp guiApp) {
            hasShownBoardSizeOptions = true;
        }
    }
}