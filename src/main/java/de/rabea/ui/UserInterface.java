package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public interface UserInterface {

    void displayBoard(Board board, Player player);

    GameMode getGameModeFromUser();

    int getBoardDimensionFromUser();

    void announceGameEnd(Mark lastPlayedMark, boolean winner);

    boolean playAgain();
}
