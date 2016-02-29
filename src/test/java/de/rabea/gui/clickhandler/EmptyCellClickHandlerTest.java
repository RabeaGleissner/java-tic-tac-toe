package de.rabea.gui.clickhandler;

import de.rabea.game.Board;
import de.rabea.gui.GuiApp;
import de.rabea.gui.GuiPlayer;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.UserInterface;
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

        public GuiAppStub(UserInterface userInterface, PlayerFactory playerFactory) {
            super(userInterface, playerFactory);
        }

        @Override
        public void playOneRound(Board board) {

        }
    }
}