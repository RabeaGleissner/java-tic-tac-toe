package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;
import de.rabea.ui.UserInterface;

public class JavaFXUi implements UserInterface {

    private GuiPlayer guiPlayer;
    private ViewUpdater viewUpdater;
    private GuiApp guiApp;

    public JavaFXUi(GuiPlayer guiPlayer, ViewUpdater viewUpdater, GuiApp guiApp) {
        this.guiPlayer = guiPlayer;
        this.viewUpdater = viewUpdater;
        this.guiApp = guiApp;
    }

    @Override
    public void displayBoard(Board board) {
        viewUpdater.showBoard(guiPlayer, board, guiApp);
    }

    @Override
    public GameMode getGameModeFromUser() {
        return GameMode.HumanVsHuman;
    }

    @Override
    public int getBoardDimensionFromUser() {
        return 3;
    }

    @Override
    public void announceGameEnd(Mark lastPlayedMark, boolean winner) {
        viewUpdater.showGameOverView(guiApp);
    }

    @Override
    public boolean playAgain() {
        return false;
    }
}
