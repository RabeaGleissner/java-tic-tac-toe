package de.rabea.gui;

import de.rabea.game.Board;

public class ViewUpdaterSpy extends ViewUpdater {
    public boolean hasShownBoard = false;
    public boolean hasShownBoardSizeOptions = false;
    public boolean hasShownGameModeOptions = false;
    public boolean showBoardMethodCalledWithCorrectBoolean;

    public ViewUpdaterSpy() {
        super(null);
    }

    @Override
    public void showBoard(GuiPlayer guiPlayer, Board board, GuiApp guiApp, boolean positionFull) {
        hasShownBoard = true;
        if (positionFull) {
            showBoardMethodCalledWithCorrectBoolean = true;
        }
    }

    @Override
    public void showBoardSizeOptionsView(GuiApp guiApp) {
        hasShownBoardSizeOptions = true;
    }

    @Override
    public void showGameModeOptions(GuiApp guiApp) {
        hasShownGameModeOptions = true;
    }

}
