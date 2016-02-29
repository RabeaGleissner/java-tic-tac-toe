package de.rabea.gui.clickhandler;

import de.rabea.gui.ClickHandler;
import de.rabea.gui.GuiApp;

public class ReplayClickHandler implements ClickHandler {

    private final GuiApp guiApp;

    public ReplayClickHandler(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void action(String position) {
        guiApp.displayGameModeOptions();
    }
}
