package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.GameMode;
import de.rabea.player.PlayerFactory;
import de.rabea.player.UnbeatableComputerPlayer;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GuiAppTest {
    private ViewUpdaterSpy viewUpdaterSpy;

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
        viewUpdaterSpy = new ViewUpdaterSpy();
    }

    @Test
    public void displaysGameOptions() {
        GuiApp guiApp = new GuiApp(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        guiApp.createGameAndGetBoardSize(GameMode.GuiHumanVsComputer);

        assertTrue(viewUpdaterSpy.hasShownBoardSizeOptions);
    }

    @Test
    public void creates3x3Board() {
        GuiApp guiApp = new GuiApp(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        Board board = guiApp.createBoard("3x3");

        assertEquals(3, board.getDimension());
    }

    @Test
    public void creates4x4Board() {
        GuiApp guiApp = new GuiApp(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        Board board = guiApp.createBoard("4x4");

        assertEquals(4, board.getDimension());
    }

    @Test
    public void showsOptionsForGameMode() {
        GuiApp guiApp = new GuiApp(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        guiApp.displayGameModeOptions();
        assertTrue(viewUpdaterSpy.hasShownGameModeOptions);
    }

    @Test
    public void createsANewGame() {
        GuiApp guiApp = new GuiApp(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        Game game = guiApp.createNewGame(GameMode.GuiHumanVsComputer);
        assertTrue(game.getPlayer() instanceof GuiPlayer);
        assertTrue(game.getOpponent() instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void preparesGameAndShowsBoard() {
        GuiApp guiApp = new GuiApp(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        guiApp.createGameAndGetBoardSize(GameMode.GuiHumanVsGuiHuman);
        guiApp.startGame("3x3");

        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

}