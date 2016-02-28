package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.player.PlayerFactory;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;

public class ViewUpdaterTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void showsBoardSizeOptions() {
        Scene scene = new Scene(new GridPane());
        ViewUpdater viewUpdater = new ViewUpdater(scene);
        viewUpdater.showBoardSizeOptionsView(new GuiApp(viewUpdater, new PlayerFactory(null)));
        Label label = (Label) scene.getRoot().getChildrenUnmodifiable().get(0);

        assertEquals("Please select a board size:", label.getText());
    }

    @Test
    public void showsBoard() {
        Scene scene = new Scene(new GridPane());
        ViewUpdater viewUpdater = new ViewUpdater(scene);
        viewUpdater.showBoard(new GuiPlayer(X), new Board(3), new GuiApp(viewUpdater, new PlayerFactory(null)), false);
        int numberOfButtons = scene.getRoot().getChildrenUnmodifiable().size();

        assertEquals(9, numberOfButtons);
    }

    @Test
    public void showsGameOverView() {
        Scene scene = new Scene(new GridPane());
        ViewUpdater viewUpdater = new ViewUpdater(scene);
        viewUpdater.showGameOverView(new GuiApp(viewUpdater, new PlayerFactory(null)), X, false);
        Button replayButton = (Button) scene.getRoot().getChildrenUnmodifiable().get(1);

        assertEquals("Play again", replayButton.getText());
    }
}