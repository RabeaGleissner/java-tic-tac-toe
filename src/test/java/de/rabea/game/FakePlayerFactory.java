package de.rabea.game;

import java.util.ArrayList;
import java.util.Arrays;

import static de.rabea.game.Mark.*;

public class FakePlayerFactory implements PlayerFactory {

    @Override
    public Player createPlayer1(GameMode gameMode) {
        return new FakePlayer(X, new ArrayList<>(Arrays.asList(0,1,2)));
    }

    @Override
    public Player createPlayer2(GameMode gameMode) {
        return new FakePlayer(O, new ArrayList<>(Arrays.asList(4,7)));
    }
}
