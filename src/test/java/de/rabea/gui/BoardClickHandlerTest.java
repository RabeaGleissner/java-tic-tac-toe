package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.assertEquals;

public class BoardClickHandlerTest {

    @Test
    public void hasNewPositionAvailable() {
        GuiPlayer guiPlayer = new GuiPlayer(O);
        Board board = new Board(3);
        BoardClickHandler boardClickHandler = new BoardClickHandler(guiPlayer, new GuiAppStub(new ViewUpdater(new Scene(new GridPane()))), board);
        boardClickHandler.action("3");

        assertEquals(3, guiPlayer.getPosition(board));
    }

    public class GuiAppStub extends GuiApp {

        public GuiAppStub(ViewUpdater viewUpdater) {
            super(viewUpdater);
        }

        @Override
        public void startGame(Board board, GuiPlayer guiPlayer) {

        }
    }
}