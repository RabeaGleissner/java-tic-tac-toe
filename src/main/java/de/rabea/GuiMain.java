package de.rabea;

import de.rabea.game.GameRunner;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.ViewUpdater;
import de.rabea.player.GuiPlayerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GuiMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new StackPane(), 500, 500);
        scene.getStylesheets().add(GuiMain.class.getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        JavaFXUi ui = new JavaFXUi(new ViewUpdater(scene));
        GameRunner gameRunner = new GameRunner(ui, new GuiPlayerFactory());
        ui.setGameRunner(gameRunner);
        gameRunner.displayGameModeOptions();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
