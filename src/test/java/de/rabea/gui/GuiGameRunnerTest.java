package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.GameMode;
import de.rabea.game.GameRunner;
import de.rabea.player.PlayerFactory;
import de.rabea.player.UnbeatableComputerPlayer;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

////public class GameRunnerTest {
////    private ViewUpdaterSpy viewUpdaterSpy;
////
////    @Before
////    public void setUp() throws Exception {
////        new JFXPanel();
////        viewUpdaterSpy = new ViewUpdaterSpy();
////    }
////
////    @Test
////    public void displaysGameOptions() {
////        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
////        gameRunner.setGameAndDisplayBoardSizeOptions(GameMode.GuiHumanVsComputer);
////
////        assertTrue(viewUpdaterSpy.hasShownBoardSizeOptions);
////    }
////
////    @Test
////    public void creates3x3Board() {
////        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
////        Board board = gameRunner.createBoard(3);
////
////        assertEquals(3, board.getDimension());
////    }
////
////    @Test
////    public void creates4x4Board() {
////        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
////        Board board = gameRunner.createBoard(4);
////
////        assertEquals(4, board.getDimension());
////    }
////
////    @Test
////    public void showsOptionsForGameMode() {
////        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
////        gameRunner.displayGameModeOptions();
////        assertTrue(viewUpdaterSpy.hasShownGameModeOptions);
////    }
////
////    @Test
////    public void createsANewGame() {
////        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
////        Game game = gameRunner.createGame(GameMode.GuiHumanVsComputer);
////        assertTrue(game.getPlayer() instanceof GuiPlayer);
////        assertTrue(game.getOpponent() instanceof UnbeatableComputerPlayer);
////    }
////
////    @Test
////    public void preparesGameAndShowsBoard() {
////        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
////        gameRunner.setGameAndDisplayBoardSizeOptions(GameMode.GuiHumanVsGuiHuman);
////        gameRunner.createBoardAndPlay(3);
////
////        assertTrue(viewUpdaterSpy.hasShownBoard);
////    }
//
//}