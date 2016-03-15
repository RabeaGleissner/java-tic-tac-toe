package de.rabea.game;

import de.rabea.console.ConsoleUi;
import de.rabea.console.FakeConsoleUserInterface;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.ViewUpdaterSpy;
import de.rabea.player.*;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.GameMode.HumanVsComputer;
import static de.rabea.game.GameMode.HumanVsHuman;
import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static de.rabea.player.FakePlayerMove.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameRunnerTest {
    private FakeConsoleUserInterface fakeConsoleUI;
    private ViewUpdaterSpy viewUpdaterSpy;

    @Before
    public void setup() {
        fakeConsoleUI = new FakeConsoleUserInterface();
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

        assertEquals(3, board.getDimension());
    }

    @Test
    public void showsOptionsForGameMode() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        gameRunner.displayGameModeOptions();

        assertTrue(viewUpdaterSpy.hasShownGameModeOptions);
    }

    @Test
    public void createsNewGameWithGuiPlayerAndUnbeatablePlayer() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        Game game = gameRunner.createGame(GameMode.GuiHumanVsComputer);

        assertTrue(game.getPlayer1() instanceof GuiPlayer);
        assertTrue(game.getPlayer2() instanceof UnbeatableComputerPlayer);
    }

    @Test
    public void preparesGameAndShowsBoard() {
        GameRunner gameRunner = new GameRunner(new JavaFXUi(viewUpdaterSpy), new PlayerFactory(null));
        gameRunner.setGameAndDisplayBoardSizeOptions(GameMode.GuiHumanVsGuiHuman);
        gameRunner.playWithFreshBoard(3);

        assertTrue(viewUpdaterSpy.hasShownBoard);
    }

    @Test
    public void playsHuman3x3ConsoleGameTwice() {
        fakeConsoleUI.setGameMode(HumanVsHuman, HumanVsHuman);
        PlayerFactoryWithTwoConsolePlayers playerFactoryWithTwoFakeHumans =
                new PlayerFactoryWithTwoConsolePlayers(fakeConsoleUI,
                        createFakeConsolePlayer1WithInput(), createFakeConsolePlayer2WithInput());
        fakeConsoleUI.setReplayChoice("yes", "no");
        fakeConsoleUI.setBoardDimensions(3,3);
        GameRunner gameRunner = new GameRunner(fakeConsoleUI, playerFactoryWithTwoFakeHumans);
        gameRunner.setUpConsoleGameAndPlay();
        assertEquals(2, fakeConsoleUI.countAnnounceGameEndCalls);
    }

    @Test
    public void createsGameWithHumanAndComputerPlayer() {
        GameRunner gameRunner = new GameRunner(fakeConsoleUI,
                new PlayerFactoryWithFakeComputerPlayer(fakeConsoleUI, createFakeComputerPlayerWithInput()));
        Game game =  gameRunner.createGame(HumanVsComputer);
        assertTrue(game.getPlayer1() instanceof ConsolePlayer);
        assertTrue(game.getPlayer2() instanceof FakeComputerPlayer);
    }

    private FakeComputerPlayer createFakeComputerPlayerWithInput() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeComputerPlayer.willMakeMoves(BOTTOM_LEFT, BOTTOM_CENTER);
        return fakeComputerPlayer;
    }

    private FakeConsolePlayer createFakeConsolePlayer1WithInput() {
        FakeConsolePlayer fakeConsolePlayer = new FakeConsolePlayer(new FakeConsoleUserInterface(), X);
        fakeConsolePlayer.willMakeMoves(TOP_LEFT, TOP_CENTRE, TOP_RIGHT, TOP_LEFT, TOP_CENTRE, TOP_RIGHT);
        return fakeConsolePlayer;
    }

    private FakeConsolePlayer createFakeConsolePlayer2WithInput() {
        FakeConsolePlayer fakeConsolePlayer = new FakeConsolePlayer(new FakeConsoleUserInterface(), X);
        fakeConsolePlayer.willMakeMoves(BOTTOM_CENTER, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_LEFT);
        return fakeConsolePlayer;
    }

    private class PlayerFactoryWithFakeComputerPlayer extends PlayerFactory {
        private final FakeComputerPlayer fakeComputerPlayer;

        public PlayerFactoryWithFakeComputerPlayer(ConsoleUi userInterface, FakeComputerPlayer fakeComputerPlayer) {
            super(userInterface);
            this.fakeComputerPlayer = fakeComputerPlayer;
        }

        @Override
        public Player createPlayer1(GameMode gameMode) {
            return new FakeConsolePlayer(fakeConsoleUI, X);
        }

        @Override
        public Player createPlayer2(GameMode gameMode) {
            return fakeComputerPlayer;
        }
    }

    private class PlayerFactoryWithTwoConsolePlayers extends PlayerFactory {
        private final FakeConsolePlayer fakePlayer1;
        private final FakeConsolePlayer fakePlayer2;

        public PlayerFactoryWithTwoConsolePlayers(ConsoleUi userInterface, FakeConsolePlayer player1, FakeConsolePlayer player2) {
            super(userInterface);
            this.fakePlayer1 = player1;
            this.fakePlayer2 = player2;
        }

        @Override
        public Player createPlayer1(GameMode gameMode) {
            return fakePlayer1;
        }

        @Override
        public Player createPlayer2(GameMode gameMode) {
            return fakePlayer2;
        }
    }
}