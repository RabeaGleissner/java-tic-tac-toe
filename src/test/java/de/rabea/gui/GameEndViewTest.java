package de.rabea.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameEndViewTest {

    private GuiApp guiApp;

    @Before
    public void setUp() {
        new JFXPanel();
        guiApp = new GuiApp(new ViewUpdater(new Scene(new GridPane())));
    }

    @Test
    public void hasGameOverMessage() {
        GameEndView gameEndView = new GameEndView();
        Parent gridPane = gameEndView.draw(guiApp);
        Text text = (Text) gridPane.getChildrenUnmodifiable().get(0);
        assertEquals("Game over", text.getText());
    }

    @Test
    public void displaysReplayButton() {
        GameEndView gameEndView = new GameEndView();
        Parent gridPane = gameEndView.draw(guiApp);
        Button button = (Button) gridPane.getChildrenUnmodifiable().get(1);
        assertEquals("Click to play again", button.getText());
    }
}