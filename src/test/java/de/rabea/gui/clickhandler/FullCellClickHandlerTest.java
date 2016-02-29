package de.rabea.gui.clickhandler;

import de.rabea.game.Board;
import de.rabea.gui.GuiApp;
import de.rabea.gui.GuiPlayer;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.ViewUpdaterSpy;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FullCellClickHandlerTest {

    @Test
    public void callsShowBoardMethodWithbooleanSetToTrueForACellInUse() {
        ViewUpdaterSpy viewUpdaterSpy = new ViewUpdaterSpy();
        FullCellClickHandler fullCellClickHandler = new FullCellClickHandler(viewUpdaterSpy, new GuiPlayer(null), new Board(3), new GuiApp(new JavaFXUi(viewUpdaterSpy), null));
        fullCellClickHandler.action(null);

        assertTrue(viewUpdaterSpy.showPositionFullWarning);

    }
}