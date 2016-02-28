package de.rabea.gui;

import de.rabea.player.PlayerFactory;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static junit.framework.TestCase.assertEquals;

public class GameEndViewTest {

    private GuiApp guiApp;

    @Before
    public void setUp() {
        new JFXPanel();
        guiApp = new GuiApp(new ViewUpdater(new Scene(new GridPane())), new PlayerFactory(null));
    }

    @Test
    public void hasGameOverMessage() {
        GameEndView gameEndView = new GameEndView();
        Parent gridPane = gameEndView.draw(guiApp, X, true);
        Label text = (Label) gridPane.getChildrenUnmodifiable().get(0);
        assertEquals("Game over. Winner is X.", text.getText());
    }

    @Test
    public void displaysReplayButton() {
        GameEndView gameEndView = new GameEndView();
        Parent gridPane = gameEndView.draw(guiApp, O, true);
        Button button = (Button) gridPane.getChildrenUnmodifiable().get(1);
        assertEquals("Play again", button.getText());
    }
}