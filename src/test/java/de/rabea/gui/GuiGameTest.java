package de.rabea.gui;

import de.rabea.game.Board;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GuiGameTest {
    GuiPlayer guiPlayer;
    Board board;

    @Before
    public void setup() {
        guiPlayer = new GuiPlayer();
        board = new Board(3);
    }

    @Test
    public void playsOneRound() {
        GuiGame guiGame = new GuiGame(board, guiPlayer);
        guiGame.playRound(1);
        assertFalse(board.isPositionAvailable(1));
    }

    @Test
    public void playsGameWithPositionsFromGuiPlayer() {
        FakeGuiPlayer fakeGuiPlayer = new FakeGuiPlayer();
        GuiGame guiGame = new GuiGame(board, fakeGuiPlayer);

        fakeGuiPlayer.makeClicks(0, 1, 2);
        guiGame.playGame();
        assertFalse(board.isPositionAvailable(2));
        assertTrue(board.gameOver());
    }

    private class FakeGuiPlayer extends GuiPlayer {
        private List<Integer> clicks = new LinkedList<>();
        private int position;

        @Override
        public void click(int clicked) {
            position = clicks.remove(0);
        }

        @Override
        public int clickedPosition() {
            position = clicks.remove(0);
            return position;
        }

        @Override
        public boolean hasNewMove() {
            return position != -1;
        }

        public void makeClicks(Integer... userClicks) {
            clicks.addAll(Arrays.asList(userClicks));
        }
    }
}