package de.rabea.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameOverViewTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void hasGameOverMessage() {
        GameOverView gameOverView = new GameOverView();
        Parent gridPane = gameOverView.draw();
        Text text = (Text) gridPane.getChildrenUnmodifiable().get(0);
        assertEquals("Game over", text.getText());
    }

    @Test
    public void displaysReplayButton() {
        GameOverView gameOverView = new GameOverView();
        Parent gridPane = gameOverView.draw();
        Button button = (Button) gridPane.getChildrenUnmodifiable().get(1);
        assertEquals("Click to play again", button.getText());
    }
}