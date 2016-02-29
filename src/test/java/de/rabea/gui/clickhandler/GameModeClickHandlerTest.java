package de.rabea.gui.clickhandler;

import de.rabea.game.GameMode;
import de.rabea.game.GameRunner;
import org.junit.Test;

import static de.rabea.game.GameMode.GuiHumanVsComputer;
import static de.rabea.game.GameMode.GuiHumanVsGuiHuman;
import static org.junit.Assert.assertEquals;

public class GameModeClickHandlerTest {

    @Test
    public void callsGuiAppMethodToCreateGameWithGhvHGameMode() {
        GameRunnerSpy spy = new GameRunnerSpy();
        GameModeClickHandler clickHandler = new GameModeClickHandler(spy);
        clickHandler.action("HumanvsHuman");
        assertEquals(GuiHumanVsGuiHuman, spy.createsGameWithThisMode);
    }

    @Test
    public void callsGuiAppMethodToCreateGameWithGhvCGameMode() {
        GameRunnerSpy spy = new GameRunnerSpy();
        GameModeClickHandler clickHandler = new GameModeClickHandler(spy);
        clickHandler.action("HumanvsComputer");
        assertEquals(GuiHumanVsComputer, spy.createsGameWithThisMode);
    }

    private class GameRunnerSpy extends GameRunner {

        private GameMode createsGameWithThisMode = null;

        public GameRunnerSpy() {
            super(null, null);
        }

        @Override
        public void setGameAndDisplayBoardSizeOptions(GameMode gameMode) {
            createsGameWithThisMode = gameMode;
        }
    }
}