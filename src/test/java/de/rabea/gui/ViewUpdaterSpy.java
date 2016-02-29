package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;

public class ViewUpdaterSpy extends ViewUpdater {
    public boolean hasShownBoard = false;
    public boolean hasShownBoardSizeOptions = false;
    public boolean hasShownGameModeOptions = false;
    public boolean showPositionFullWarning;
    public boolean hasShownGameEndView = false;

    public ViewUpdaterSpy() {
        super(null);
    }

    @Override
    public void showBoard(GuiPlayer guiPlayer, Board board, GuiApp guiApp, boolean positionFull) {
        hasShownBoard = true;
        if (positionFull) {
            showPositionFullWarning = true;
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

    @Override
    public void showGameEndView(GuiApp guiApp, Mark lastPlayedMark, boolean winner) {
        hasShownGameEndView = true;
    }

}
