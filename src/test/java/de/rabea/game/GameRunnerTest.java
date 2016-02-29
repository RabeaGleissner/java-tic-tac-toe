package de.rabea.game;

import de.rabea.console.ConsoleUi;
import de.rabea.console.FakeUserInterface;
import de.rabea.gui.GuiPlayer;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.ViewUpdaterSpy;
import de.rabea.player.FakeComputerPlayer;
import de.rabea.player.HumanPlayer;
import de.rabea.player.PlayerFactory;
import de.rabea.player.UnbeatableComputerPlayer;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.GameMode.HumanVsComputer;
import static de.rabea.game.GameMode.HumanVsHuman;
import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameRunnerTest {
    private FakeUserInterface fakeUserInterface;
    private PlayerFactory playerFactory;
    private ViewUpdaterSpy viewUpdaterSpy;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        playerFactory = new PlayerFactory(fakeUserInterface);
        viewUpdaterSpy = new ViewUpdaterSpy();
    }

    @Test
    public void displaysGameOptionsOnGui() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        gameRunner.setGameAndDisplayBoardSizeOptions(GameMode.GuiHumanVsComputer);

        assertTrue(viewUpdaterSpy.hasShownBoardSizeOptions);
    }

    @Test
    public void creates3x3Board() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        Board board = gameRunner.createBoard(3);

        TestCase.assertEquals(3, board.getDimension());
    }

    @Test
    public void creates4x4Board() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        Board board = gameRunner.createBoard(4);

        TestCase.assertEquals(4, board.getDimension());
    }

    @Test
    public void showsOptionsForGameMode() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        gameRunner.displayGameModeOptions();
        assertTrue(viewUpdaterSpy.hasShownGameModeOptions);
    }

    @Test
    public void createsANewGame() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        Game game = gameRunner.createGame(GameMode.GuiHumanVsComputer);
        assertTrue(game.getPlayer() instanceof GuiPlayer);
        assertTrue(game.getOpponent() instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void preparesGameAndShowsBoard() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        gameRunner.setGameAndDisplayBoardSizeOptions(GameMode.GuiHumanVsGuiHuman);
        gameRunner.createBoardAndPlay(3);

        assertTrue(viewUpdaterSpy.hasShownBoard);
    }
    @Test
    public void createsTwoHumanPlayersWhenRequestedForConsoleGame() {
        fakeUserInterface.fakeConsoleInputForOneHvH3x3Game();
        GameRunner gameRunner = new GameRunner(fakeUserInterface, playerFactory);
        Game game = gameRunner.createGame(HumanVsHuman);
        assertTrue(game.getPlayer() instanceof HumanPlayer);
        assertTrue(game.getOpponent() instanceof HumanPlayer);
    }

    @Test
    public void playsHuman3x3ConsoleGameTwice() {
        fakeUserInterface.fakeConsoleInputForTwoHvH3x3Games();
        GameRunner gameRunner = new GameRunner(fakeUserInterface, playerFactory);
        gameRunner.setUpConsoleGameAndPlay();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void createsHumanAndComputerPlayerForConsoleGame() {
        GameRunner gameRunner = new GameRunner(fakeUserInterface,
                new FakePlayerFactory(fakeUserInterface, createFakeComputerPlayerWithInput()));
        Game game =  gameRunner.createGame(HumanVsComputer);
        assertTrue(game.getPlayer() instanceof HumanPlayer);
        assertTrue(game.getOpponent() instanceof FakeComputerPlayer);
    }

    private FakeComputerPlayer createFakeComputerPlayerWithInput() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeComputerPlayer.giveNumbers(6, 7);
        return fakeComputerPlayer;
    }

    private class FakePlayerFactory extends PlayerFactory {
        private final FakeComputerPlayer fakeComputerPlayer;

        public FakePlayerFactory(ConsoleUi userInterface, FakeComputerPlayer fakeComputerPlayer) {
            super(userInterface);
            this.fakeComputerPlayer = fakeComputerPlayer;
        }

        @Override
        public Player createPlayer(GameMode gameMode) {
            return new HumanPlayer(fakeUserInterface, X);
        }

        @Override
        public Player createOpponent(GameMode gameMode) {
            return fakeComputerPlayer;
        }
    }
}