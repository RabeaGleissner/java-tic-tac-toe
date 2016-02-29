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

        assertTrue(viewUpdaterSpy.showPositionFullWarning);

    }
}