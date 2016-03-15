package de.rabea.gui.clickhandler;

import de.rabea.gui.GameContext;
import de.rabea.player.GuiPlayer;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameModeClickHandlerTest {

    @Test
    @Ignore
    public void createsHvHGame() {
        GameContext context = new GameContext();
        GameModeClickHandler clickHandler = new GameModeClickHandler(context);
        clickHandler.action("HumanvsHuman");
        assertEquals(GuiPlayer.class, context.getGame().getPlayer1());
    }
}