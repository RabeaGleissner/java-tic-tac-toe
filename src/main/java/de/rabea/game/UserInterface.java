package de.rabea.game;

public interface UserInterface {

    void displayBoard(Board board, Player player);

    GameMode getGameModeFromUser();

    int getBoardDimensionFromUser();

    void announceGameEnd(Mark lastPlayedMark, boolean winner);

    boolean playAgain();
}
