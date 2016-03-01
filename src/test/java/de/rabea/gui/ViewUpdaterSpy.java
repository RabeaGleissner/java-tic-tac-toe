package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.GameRunner;
import de.rabea.game.Mark;
import de.rabea.player.GuiPlayer;

public class ViewUpdaterSpy extends ViewUpdater {
    public boolean hasShownBoard = false;
    public boolean hasShownBoardSizeOptions = false;
    public boolean hasShownGameModeOptions = false;
    public boolean showPositionFullWarning;
    public boolean hasShownGameEndView = false;
    public GameRunner gameRunnerThatWasPassedIn = null;

    public ViewUpdaterSpy() {
        super(null);
    }

    @Override
    public void showBoard(GuiPlayer guiPlayer, Board board, GameRunner gameRunner, boolean positionFull) {
        hasShownBoard = true;
        if (positionFull) {
            showPositionFullWarning = true;
        }
    }

    @Override
    public void showBoardSizeOptionsView(GameRunner gameRunner) {
        hasShownBoardSizeOptions = true;
    }

    @Override
    public void showGameModeOptions(GameRunner gameRunner) {
        hasShownGameModeOptions = true;
        gameRunnerThatWasPassedIn = gameRunner;
    }

    @Override
    public void showGameEndView(GameRunner gameRunner, Mark lastPlayedMark, boolean winner) {
        hasShownGameEndView = true;
    }

}
