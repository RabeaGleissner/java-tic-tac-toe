package de.rabea.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new StackPane(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        GuiApp guiApp = new GuiApp(new ViewUpdater(scene));
        guiApp.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
