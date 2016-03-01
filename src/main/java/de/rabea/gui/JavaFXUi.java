package de.rabea.gui;

import de.rabea.game.*;
import de.rabea.player.GuiPlayer;

public class JavaFXUi implements UserInterface {

    private final ViewUpdater viewUpdater;
    private GameRunner gameRunner;

    public JavaFXUi(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }

    public void setGameRunner(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    @Override
    public void displayBoard(Board board, Player player) {
        viewUpdater.showBoard((GuiPlayer) player, board, gameRunner, false);
    }

    @Override
    public GameMode getGameModeFromUser() {
        viewUpdater.showGameModeOptions(gameRunner);
        return null;
    }

    @Override
    public int getBoardDimensionFromUser() {
        viewUpdater.showBoardSizeOptionsView(gameRunner);
        return 3;
    }

    @Override
    public void announceGameEnd(Mark lastPlayedMark, boolean winner) {
        viewUpdater.showGameEndView(gameRunner, lastPlayedMark, winner);
    }

    @Override
    public boolean playAgain() {
        return false;
    }
}
