package de.rabea.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new StackPane(), 500, 500);
        scene.getStylesheets().add(Main.class.getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        ViewUpdater viewUpdater = new ViewUpdater(scene);
        GuiApp guiApp = new GuiApp(viewUpdater);
        guiApp.displayGameOptions();
    }

    public static void main(String[] args) {
        launch(args);
    }
}