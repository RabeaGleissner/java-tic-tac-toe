package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuiPlayerTest {

    Board board;
    ViewUpdater viewUpdater;

    @Before
    public void setup() {
        board = new Board(3);
        viewUpdater = new ViewUpdater(new Scene(new GridPane()));
    }

    @Test
    public void playsOneRoundOnClick() {
        GuiPlayer guiPlayer = new GuiPlayer(new GuiGame(board, viewUpdater));
        guiPlayer.click(1);
        assertFalse(board.isPositionAvailable(1));
    }

    @Test
    public void switchesXMarkToOMark() {
        GuiPlayer guiPlayer = new GuiPlayer(new GuiGame(board, viewUpdater));
        guiPlayer.switchMark();
        assertEquals(Mark.O, guiPlayer.getCurrentMark());
    }

    @Test
    public void switchesOMarkToXMark() {
        GuiPlayer guiPlayer = new GuiPlayer(new GuiGame(board, viewUpdater));
        guiPlayer.switchMark();
        guiPlayer.switchMark();
        assertEquals(Mark.X, guiPlayer.getCurrentMark());
    }
}