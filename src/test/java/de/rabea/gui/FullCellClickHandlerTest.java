package de.rabea.gui;

import de.rabea.game.Board;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FullCellClickHandlerTest {

    @Test
    public void callsShowBoardMethodWithbooleanSetToTrueForACellInUse() {
        ViewUpdaterSpy viewUpdaterSpy = new ViewUpdaterSpy();
        FullCellClickHandler fullCellClickHandler = new FullCellClickHandler(viewUpdaterSpy, new GuiPlayer(null), new Board(3), new GuiApp(viewUpdaterSpy, null));
        fullCellClickHandler.action(null);

        assertTrue(viewUpdaterSpy.showBoardMethodCalledWithCorrectBoolean);

    }

    private class ViewUpdaterSpy extends ViewUpdater {

        private boolean showBoardMethodCalledWithCorrectBoolean = false;

        public ViewUpdaterSpy() {
            super(null);
        }

        @Override
        public void showBoard(GuiPlayer guiPlayer, Board board, GuiApp guiApp, boolean positionFull) {
            if (positionFull) {
                showBoardMethodCalledWithCorrectBoolean = true;
            }

        }
    }
}