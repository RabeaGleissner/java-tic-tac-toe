package de.rabea.gui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardClickHandlerTest {

    @Test
    public void hasNewPositionAvailable() {
        ClickCarrier clickCarrier = new ClickCarrier();
        BoardClickHandler boardClickHandler = new BoardClickHandler(clickCarrier,
                new GuiAppStub(new ViewUpdater(new Scene(new GridPane()))));
        boardClickHandler.action("3");

        assertEquals(3, clickCarrier.getMove());
    }

    public class GuiAppStub extends GuiApp {

        public GuiAppStub(ViewUpdater viewUpdater) {
            super(viewUpdater);
        }

        @Override
        public void startGame() {

        }
    }
}