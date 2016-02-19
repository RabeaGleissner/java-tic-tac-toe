package de.rabea.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameEndViewTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void hasGameOverMessage() {
        GameEndView gameEndView = new GameEndView();
        Parent gridPane = gameEndView.draw();
        Text text = (Text) gridPane.getChildrenUnmodifiable().get(0);
        assertEquals("Game over", text.getText());
    }

    @Test
    public void displaysReplayButton() {
        GameEndView gameEndView = new GameEndView();
        Parent gridPane = gameEndView.draw();
        Button button = (Button) gridPane.getChildrenUnmodifiable().get(1);
        assertEquals("Click to play again", button.getText());
    }
}