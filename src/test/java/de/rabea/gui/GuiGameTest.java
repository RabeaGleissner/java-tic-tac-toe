package de.rabea.gui;

import de.rabea.game.Board;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertFalse;

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
        guiGame.playRound(1, X);
        assertFalse(board.isPositionAvailable(1));
    }

    private static class FakeBoardView extends BoardView {

        public FakeBoardView(ClickHandler clickHandler) {
            super(clickHandler);
        }

        @Override
        public Parent draw(Board board) {

            return super.draw(board);
        }
    }

}