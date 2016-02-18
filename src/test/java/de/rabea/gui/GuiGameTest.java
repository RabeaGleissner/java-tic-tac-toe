package de.rabea.gui;

import de.rabea.game.Board;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GuiGameTest {
    GuiPlayer guiPlayer;
    Board board;
    GuiGame guiGame;
    ViewUpdater viewUpdater;

    @Before
    public void setup() {
        viewUpdater = new ViewUpdater(new Scene(new GridPane()));
        board = new Board(3);
        guiGame = new GuiGame(board, viewUpdater);
        guiPlayer = new GuiPlayer(guiGame);
    }

    @Test
    public void playsOneRound() {
        GuiGame guiGame = new GuiGame(board, viewUpdater);
        guiGame.playRound(1);
        assertFalse(board.isPositionAvailable(1));
    }

    @Test
    public void displaysGameOverViewIfGameIsOver() {
        ViewUpdaterSpy viewUpdaterSpy = new ViewUpdaterSpy(new Scene(new GridPane()));
        GuiGame guiGame = new GuiGame(board, viewUpdaterSpy);
        guiGame.playRound(0);
        guiGame.playRound(1);
        guiGame.playRound(2);
        assertTrue(viewUpdaterSpy.displaysGameOverView);
    }

    private class ViewUpdaterSpy extends ViewUpdater {
        private boolean displaysGameOverView = false;

        public ViewUpdaterSpy(Scene scene) {
            super(scene);
        }

        public void showGameOverView() {
            displaysGameOverView = true;
        }
    }
}