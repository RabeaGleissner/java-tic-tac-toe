package de.rabea.gui.view;

import de.rabea.gui.GuiApp;
import de.rabea.gui.ViewUpdater;
import de.rabea.player.PlayerFactory;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardSizeViewTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void asksUserToSelectBoardSize() {
        BoardSizeView boardSizeView = new BoardSizeView();
        Parent parent = boardSizeView.draw(new GuiApp(new ViewUpdater(new Scene(new GridPane())), new PlayerFactory(null)));
        Label label = (Label) parent.getChildrenUnmodifiable().get(0);

        assertEquals("Please select a board size:", label.getText());
    }

    @Test
    public void hasButtonToChoose3x3Board() {
        BoardSizeView boardSizeView = new BoardSizeView();
        Parent parent = boardSizeView.draw(new GuiApp(new ViewUpdater(new Scene(new GridPane())), new PlayerFactory(null)));
        Button button = (Button) parent.getChildrenUnmodifiable().get(1);

        assertEquals("3x3 board", button.getText());
    }

    @Test
    public void hasButtonToChoose4x4Board() {
        BoardSizeView boardSizeView = new BoardSizeView();
        Parent parent = boardSizeView.draw(new GuiApp(new ViewUpdater(new Scene(new GridPane())), new PlayerFactory(null)));
        Button button = (Button) parent.getChildrenUnmodifiable().get(2);

        assertEquals("4x4 board", button.getText());
    }
}