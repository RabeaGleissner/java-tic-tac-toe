package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;
import de.rabea.game.Player;
import de.rabea.ui.UserInterface;

public class JavaFXUi implements UserInterface {

    private final ViewUpdater viewUpdater;
    private final GuiApp guiApp;

    public JavaFXUi(ViewUpdater viewUpdater, GuiApp guiApp) {
        this.viewUpdater = viewUpdater;
        this.guiApp = guiApp;
    }

    @Override
    public void displayBoard(Board board, Player player) {
        viewUpdater.showBoard((GuiPlayer) player, board, guiApp);
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
