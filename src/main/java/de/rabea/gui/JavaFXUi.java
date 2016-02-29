package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;
import de.rabea.game.Player;
import de.rabea.ui.UserInterface;

public class JavaFXUi implements UserInterface {

    private final ViewUpdater viewUpdater;
    private GuiApp guiApp;

    public JavaFXUi(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void setGuiApp(GuiApp guiApp) {
        this.guiApp = guiApp;
    }

    @Override
    public void displayBoard(Board board, Player player) {
        viewUpdater.showBoard((GuiPlayer) player, board, guiApp, false);
    }

    @Override
    public GameMode getGameModeFromUser() {
        viewUpdater.showGameModeOptions(guiApp);
        return GameMode.HumanVsHuman;
    }

    @Override
    public int getBoardDimensionFromUser() {
        viewUpdater.showBoardSizeOptionsView(guiApp);
        return 3;
    }

    @Override
    public void announceGameEnd(Mark lastPlayedMark, boolean winner) {
        viewUpdater.showGameEndView(guiApp, lastPlayedMark, winner);
    }

    @Override
    public boolean playAgain() {
        return false;
    }

    @Override
    public void announceMarkDistribution(GameMode gameMode) {

    }
}
