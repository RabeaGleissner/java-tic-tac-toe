package de.rabea.gui;

import de.rabea.game.Board;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GuiGameTest {
    GuiPlayer guiPlayer;
    Board board;
    GuiGame guiGame;
    ViewUpdater viewUpdater;

    @Before
    public void setup() {
        new JFXPanel();
        viewUpdater = new ViewUpdater(new Scene(new GridPane()));
        board = new Board(3);
        guiGame = new GuiGame(board, viewUpdater);
        guiPlayer = new GuiPlayer(guiGame);
    }

    @Ignore
    @Test
    public void playsOneRound() {
        GuiGame guiGame = new GuiGame(board, viewUpdater);
        guiGame.playRound(1, X, new GuiPlayer(guiGame));
        assertFalse(board.isPositionAvailable(1));
    }

    @Test
    public void redrawsBoardAfterEachMove() {
        ViewUpdaterSpy viewUpdaterSpy = new ViewUpdaterSpy(new Scene(new GridPane()));
        GuiGame guiGame = new GuiGame(board, viewUpdaterSpy);
        guiGame.playRound(1, X, new GuiPlayer(guiGame));
        assertTrue(viewUpdaterSpy.redrawsBoard);
    }

    @Test
    public void displaysGameOverViewIfGameIsOver() {
        ViewUpdaterSpy viewUpdaterSpy = new ViewUpdaterSpy(new Scene(new GridPane()));
        GuiGame guiGame = new GuiGame(board, viewUpdaterSpy);
        guiGame.playRound(0, X, new GuiPlayer(guiGame));
        guiGame.playRound(1, X, new GuiPlayer(guiGame));
        guiGame.playRound(2, X, new GuiPlayer(guiGame));
        assertTrue(viewUpdaterSpy.displaysGameOverView);
    }

    private class ViewUpdaterSpy extends ViewUpdater {
        private boolean displaysGameOverView = false;
        private boolean redrawsBoard = false;

        public ViewUpdaterSpy(Scene scene) {
            super(scene);
        }

        public void showBoard(GuiPlayer guiPlayer, Board board) {
            redrawsBoard = true;
        }

        public void showGameOverView() {
            displaysGameOverView = true;
        }
    }
}