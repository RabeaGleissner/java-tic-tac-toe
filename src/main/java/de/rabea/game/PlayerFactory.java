package de.rabea.game;

public interface PlayerFactory {
    Player createPlayer1(GameMode gameMode);
    Player createPlayer2(GameMode gameMode);
}