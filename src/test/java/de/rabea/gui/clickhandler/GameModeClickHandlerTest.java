package de.rabea.gui.clickhandler;

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
}