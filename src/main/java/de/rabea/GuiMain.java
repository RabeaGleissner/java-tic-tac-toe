package de.rabea;

import de.rabea.gui.GuiApp;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.ViewUpdater;
import de.rabea.player.PlayerFactory;
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
        ViewUpdater viewUpdater = new ViewUpdater(scene);
        JavaFXUi ui = new JavaFXUi(viewUpdater);
        GuiApp guiApp = new GuiApp(ui, new PlayerFactory(null));
        ui.setGuiApp(guiApp);
        guiApp.displayGameModeOptions();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
