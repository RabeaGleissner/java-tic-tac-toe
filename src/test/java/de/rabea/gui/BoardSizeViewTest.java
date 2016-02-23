package de.rabea.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardSizeViewTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void asksUserToSelectBoardSize() {
        BoardSizeView boardSizeView = new BoardSizeView();
        Parent parent = boardSizeView.draw(new GuiApp(new ViewUpdater(new Scene(new GridPane()))));
        Label label = (Label) parent.getChildrenUnmodifiable().get(0);

        assertEquals("Please select a board size:", label.getText());
    }

    @Test
    public void hasButtonToChoose3x3Board() {
        BoardSizeView boardSizeView = new BoardSizeView();
        Parent parent = boardSizeView.draw(new GuiApp(new ViewUpdater(new Scene(new GridPane()))));
        Button button = (Button) parent.getChildrenUnmodifiable().get(1);

        assertTrue(button instanceof Button);
        assertEquals("3x3 board", button.getText());
    }

    @Test
    public void hasButtonToChoose4x4Board() {
        BoardSizeView boardSizeView = new BoardSizeView();
        Parent parent = boardSizeView.draw(new GuiApp(new ViewUpdater(new Scene(new GridPane()))));
        Button button = (Button) parent.getChildrenUnmodifiable().get(2);

        assertTrue(button instanceof Button);
        assertEquals("4x4 board", button.getText());
    }
}