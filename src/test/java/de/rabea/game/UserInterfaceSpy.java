package de.rabea.game;

public class UserInterfaceSpy implements UserInterface {

    public boolean boardWasDisplayed = false;
    public boolean gameEndWasAnnounced = false;
    public boolean gameModeOptionsWereDisplayed = false;
    public boolean askedForBoardDimensions = false;
    public boolean askedForReplay = false;

    @Override
    public void displayBoard(Board board, Player player) {
        boardWasDisplayed = true;
    }

    @Override
    public GameMode getGameModeFromUser() {
        gameModeOptionsWereDisplayed = true;
        return null;
    }

    @Override
    public int getBoardDimensionFromUser() {
        askedForBoardDimensions = true;
        return 0;
    }

    @Override
    public void announceGameEnd(Mark lastPlayedMark, Board board) {
        gameEndWasAnnounced = true;

    }

    @Override
    public boolean playAgain() {
        askedForReplay = true;
        return false;
    }
}
