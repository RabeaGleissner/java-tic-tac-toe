package de.rabea.gui;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertTrue;

public class JavaFXUiTest {
    private ViewUpdaterSpy viewUpdaterSpy;
    private GuiApp guiApp;

    @Before
    public void setup() {
        viewUpdaterSpy = new ViewUpdaterSpy();
        guiApp = new GuiApp(null, null);
    }

    @Test
    public void displaysBoard() {
        JavaFXUi ui = new JavaFXUi(viewUpdaterSpy, guiApp);
        ui.displayBoard(new Board(3), new GuiPlayer(X));
        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

    @Test
    public void announcesGameEnd() {
        JavaFXUi ui = new JavaFXUi(viewUpdaterSpy, guiApp);
        ui.announceGameEnd(X, true);
        assertTrue(viewUpdaterSpy.hasShownGameEndView);
    }
}