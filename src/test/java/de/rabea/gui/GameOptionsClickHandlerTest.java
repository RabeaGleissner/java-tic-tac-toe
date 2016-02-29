package de.rabea.gui;

import de.rabea.game.GameMode;
import org.junit.Test;

import static de.rabea.game.GameMode.*;
import static org.junit.Assert.assertEquals;

public class GameOptionsClickHandlerTest {

    @Test
    public void callsGuiAppMethodToCreateGameWithGhvHGameMode() {
        GuiAppSpy spy = new GuiAppSpy();
        GameOptionsClickHandler clickHandler = new GameOptionsClickHandler(spy);
        clickHandler.action("HumanvsHuman");
        assertEquals(GuiHumanVsGuiHuman, spy.createsGameWithThisMode);
    }

    @Test
    public void callsGuiAppMethodToCreateGameWithGhvCGameMode() {
        GuiAppSpy spy = new GuiAppSpy();
        GameOptionsClickHandler clickHandler = new GameOptionsClickHandler(spy);
        clickHandler.action("HumanvsComputer");
        assertEquals(GuiHumanVsComputer, spy.createsGameWithThisMode);
    }

    private class GuiAppSpy extends GuiApp {

        private GameMode createsGameWithThisMode = null;

        public GuiAppSpy() {
            super(null, null);
        }

        @Override
        public void createGameAndGetBoardSize(GameMode gameMode) {
            createsGameWithThisMode = gameMode;
        }
    }
}