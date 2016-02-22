package de.rabea.gui;

public class ReplayClickHandler implements ClickHandler {

    private GuiApp guiApp;

    public ReplayClickHandler(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void action(String position) {
        guiApp.displayGameOptions();
    }
}
