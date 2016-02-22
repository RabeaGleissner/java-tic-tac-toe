package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;
import de.rabea.ui.UserInterface;

public class JavaFXUi implements UserInterface {

    private NextGuiPlayer guiPlayer;
    private ViewUpdater viewUpdater;

    public JavaFXUi(NextGuiPlayer guiPlayer, ViewUpdater viewUpdater) {
        this.guiPlayer = guiPlayer;
        this.viewUpdater = viewUpdater;
    }

    @Override
    public void displayBoard(Board board) {
        viewUpdater.showBoard(guiPlayer, board);
    }

    @Override
    public void greet() {
        System.out.println("Hello and welcome to Tic Tac Toe!");
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
        System.out.println("Game over");

    }

    @Override
    public boolean playAgain() {
        return false;
    }
}
