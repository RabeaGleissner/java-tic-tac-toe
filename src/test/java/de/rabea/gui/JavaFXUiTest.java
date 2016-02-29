package de.rabea.gui;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertTrue;

public class JavaFXUiTest {
    private ViewUpdaterSpy viewUpdaterSpy;

    @Before
    public void setup() {
        viewUpdaterSpy = new ViewUpdaterSpy();
    }

    @Test
    public void displaysBoard() {
        JavaFXUi ui = new JavaFXUi(viewUpdaterSpy);
        ui.displayBoard(new Board(3), new GuiPlayer(X));
        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

    @Test
    public void announcesGameEnd() {
        JavaFXUi ui = new JavaFXUi(viewUpdaterSpy);
        ui.announceGameEnd(X, true);
        assertTrue(viewUpdaterSpy.hasShownGameEndView);
    }
}