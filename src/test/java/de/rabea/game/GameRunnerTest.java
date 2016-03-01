package de.rabea.game;

import de.rabea.console.ConsoleUi;
import de.rabea.console.FakeConsoleUserInterface;
import de.rabea.player.*;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.ViewUpdaterSpy;
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
    private FakeConsoleUserInterface fakeConsoleUI;
    private PlayerFactory playerFactory;
    private ViewUpdaterSpy viewUpdaterSpy;

    @Before
    public void setup() {
        fakeConsoleUI = new FakeConsoleUserInterface();
        playerFactory = new PlayerFactory(fakeConsoleUI);
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
        gameRunner.playWithFreshBoard(3);

        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

    @Test
    public void createsTwoHumanPlayersWhenRequestedForConsoleGame() {
        fakeConsoleUI.setGameMode(HumanVsHuman);
        GameRunner gameRunner = new GameRunner(fakeConsoleUI, playerFactory);
        Game game = gameRunner.createGame(HumanVsHuman);
        assertTrue(game.getPlayer() instanceof HumanPlayer);
        assertTrue(game.getOpponent() instanceof HumanPlayer);
    }

    @Test
    public void playsHuman3x3ConsoleGameTwice() {
        fakeConsoleUI.setGameMode(HumanVsHuman, HumanVsHuman);
        PlayerFactoryWithTwoFakeHumanPlayers playerFactoryWithTwoFakeHumans =
                new PlayerFactoryWithTwoFakeHumanPlayers(fakeConsoleUI, createFakeHumanPlayerWithInput(), createFakeHumanOpponentWithInput());
        fakeConsoleUI.setReplayChoice("yes", "no");
        fakeConsoleUI.setBoardDimensions(3,3);
        GameRunner gameRunner = new GameRunner(fakeConsoleUI, playerFactoryWithTwoFakeHumans);
        gameRunner.setUpConsoleGameAndPlay();
        assertEquals(2, fakeConsoleUI.countAnnounceGameEndCalls);
    }

    @Test
    public void createsHumanAndComputerPlayerForConsoleGame() {
        GameRunner gameRunner = new GameRunner(fakeConsoleUI,
                new PlayerFactoryWithFakeComputerPlayer(fakeConsoleUI, createFakeComputerPlayerWithInput()));
        Game game =  gameRunner.createGame(HumanVsComputer);
        assertTrue(game.getPlayer() instanceof HumanPlayer);
        assertTrue(game.getOpponent() instanceof FakeComputerPlayer);
    }

    private FakeComputerPlayer createFakeComputerPlayerWithInput() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeComputerPlayer.giveNumbers(6, 7);
        return fakeComputerPlayer;
    }

    private FakeHumanPlayer createFakeHumanPlayerWithInput() {
        FakeHumanPlayer fakeHumanPlayer = new FakeHumanPlayer(new FakeConsoleUserInterface(), X);
        fakeHumanPlayer.setPositions(0,1,2,0,1,2);
        return fakeHumanPlayer;
    }

    private FakeHumanPlayer createFakeHumanOpponentWithInput() {
        FakeHumanPlayer fakeHumanOpponent = new FakeHumanPlayer(new FakeConsoleUserInterface(), X);
        fakeHumanOpponent.setPositions(8,7,8,7);
        return fakeHumanOpponent;
    }

    private class PlayerFactoryWithFakeComputerPlayer extends PlayerFactory {
        private final FakeComputerPlayer fakeComputerPlayer;

        public PlayerFactoryWithFakeComputerPlayer(ConsoleUi userInterface, FakeComputerPlayer fakeComputerPlayer) {
            super(userInterface);
            this.fakeComputerPlayer = fakeComputerPlayer;
        }

        @Override
        public Player createPlayer(GameMode gameMode) {
            return new FakeHumanPlayer(fakeConsoleUI, X);
        }

        @Override
        public Player createOpponent(GameMode gameMode) {
            return fakeComputerPlayer;
        }
    }

    private class PlayerFactoryWithTwoFakeHumanPlayers extends PlayerFactory {
        private final FakeHumanPlayer fakePlayer;
        private final FakeHumanPlayer fakeOpponent;

        public PlayerFactoryWithTwoFakeHumanPlayers(ConsoleUi userInterface, FakeHumanPlayer fakeHumanPlayer, FakeHumanPlayer fakeHumanOpponent) {
            super(userInterface);
            this.fakePlayer = fakeHumanPlayer;
            this.fakeOpponent = fakeHumanOpponent;
        }

        @Override
        public Player createPlayer(GameMode gameMode) {
            return fakePlayer;
        }

        @Override
        public Player createOpponent(GameMode gameMode) {
            return fakeOpponent;
        }
    }
}