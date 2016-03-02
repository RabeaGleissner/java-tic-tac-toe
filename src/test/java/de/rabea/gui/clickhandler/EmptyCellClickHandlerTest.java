package de.rabea.gui.clickhandler;

import de.rabea.game.Board;
import de.rabea.game.GameRunner;
import de.rabea.game.UserInterface;
import de.rabea.player.GuiPlayer;
import de.rabea.player.PlayerFactory;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.assertEquals;

public class EmptyCellClickHandlerTest {

    @Test
    public void hasNewPositionAvailable() {
        GuiPlayer guiPlayer = new GuiPlayer(O);
        Board board = new Board(3);
        EmptyCellClickHandler emptyCellClickHandler = new EmptyCellClickHandler(guiPlayer, new GameRunnerStub(null, null), board);
        emptyCellClickHandler.action("3");

        assertEquals(3, guiPlayer.makeMove(board));
    }

    public class GameRunnerStub extends GameRunner {

        public GameRunnerStub(UserInterface userInterface, PlayerFactory playerFactory) {
            super(userInterface, playerFactory);
        }

        @Override
        public void playOneRound(Board board) {

        }
    }
}