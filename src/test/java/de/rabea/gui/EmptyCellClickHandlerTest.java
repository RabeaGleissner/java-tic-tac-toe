package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.player.PlayerFactory;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.assertEquals;

public class EmptyCellClickHandlerTest {

    @Test
    public void hasNewPositionAvailable() {
        GuiPlayer guiPlayer = new GuiPlayer(O);
        Board board = new Board(3);
        EmptyCellClickHandler emptyCellClickHandler = new EmptyCellClickHandler(guiPlayer, new GuiAppStub(null, null), board);
        emptyCellClickHandler.action("3");

        assertEquals(3, guiPlayer.getPosition(board));
    }

    public class GuiAppStub extends GuiApp {

        public GuiAppStub(ViewUpdater viewUpdater, PlayerFactory playerFactory) {
            super(viewUpdater, playerFactory);
        }

        @Override
        public void playOneRound(Board board) {

        }
    }
}