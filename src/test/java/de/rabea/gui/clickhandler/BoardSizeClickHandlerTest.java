package de.rabea.gui.clickhandler;

import de.rabea.game.GameRunner;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardSizeClickHandlerTest {

    @Test
    public void createsAnddisplays3x3BoardOnClickAction() {
        GameRunnerSpy guiAppSpy = new GameRunnerSpy();
        BoardSizeClickHandler handler = new BoardSizeClickHandler(guiAppSpy);
        handler.action("3x3");

        assertTrue(guiAppSpy.createBoardAndPlayWasCalled);
        assertEquals(3, guiAppSpy.givenBoardSize);
    }

    @Test
    public void createsAnddisplays4x4BoardOnClickAction() {
        GameRunnerSpy guiAppSpy = new GameRunnerSpy();
        BoardSizeClickHandler handler = new BoardSizeClickHandler(guiAppSpy);
        handler.action("4x4");

        assertTrue(guiAppSpy.createBoardAndPlayWasCalled);
        assertEquals(4, guiAppSpy.givenBoardSize);
    }
}