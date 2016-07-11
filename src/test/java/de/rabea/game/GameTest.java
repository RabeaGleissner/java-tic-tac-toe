package de.rabea.game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private Player player1;
    private Player player2;

    @Before
    public void setup() {
        player1 = new FakePlayer(X, new ArrayList<>(Arrays.asList(0, 1, 2)));
        player2 = new FakePlayer(X, new ArrayList<>(Arrays.asList(3, 4)));
    }

    @Test
    public void displaysBoard() {
        UserInterfaceSpy spy = new UserInterfaceSpy();
        Game game = new Game(spy, player1, player2);
        game.play(new Board(3));
        assertTrue(spy.boardWasDisplayed);
    }

    @Test
    public void announcesGameEndWhenGameIsOver() {
        UserInterfaceSpy spy = new UserInterfaceSpy();
        player1 = new FakePlayer(X, new ArrayList<>(Arrays.asList(0, 1, 2)));
        player2 = new FakePlayer(X, new ArrayList<>(Arrays.asList(3, 4)));
        Game game = new Game(spy, player1, player2);
        game.play(new Board(3));
        assertTrue(spy.gameEndWasAnnounced);
    }
}
