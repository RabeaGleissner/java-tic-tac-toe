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
        PlayerFactoryWithTwoFakeHumanPlayers playerFactoryWithTwoFakeHumans =
                new PlayerFactoryWithTwoFakeHumanPlayers(fakeConsoleUI, createFakeHumanPlayer1WithInput(), createFakeHumanPlayer2WithInput());
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
        assertTrue(game.getPlayer1() instanceof HumanPlayer);
        assertTrue(game.getPlayer2() instanceof FakeComputerPlayer);
    }

    private FakeComputerPlayer createFakeComputerPlayerWithInput() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeComputerPlayer.giveNumbers(6, 7);
        return fakeComputerPlayer;
    }

    private FakeHumanPlayer createFakeHumanPlayer1WithInput() {
        FakeHumanPlayer fakeHumanPlayer = new FakeHumanPlayer(new FakeConsoleUserInterface(), X);
        fakeHumanPlayer.futureMoves(0,1,2,0,1,2);
        return fakeHumanPlayer;
    }

    private FakeHumanPlayer createFakeHumanPlayer2WithInput() {
        FakeHumanPlayer fakeHumanPlayer = new FakeHumanPlayer(new FakeConsoleUserInterface(), X);
        fakeHumanPlayer.futureMoves(8,7,8,7);
        return fakeHumanPlayer;
    }

    private class PlayerFactoryWithFakeComputerPlayer extends PlayerFactory {
        private final FakeComputerPlayer fakeComputerPlayer;

        public PlayerFactoryWithFakeComputerPlayer(ConsoleUi userInterface, FakeComputerPlayer fakeComputerPlayer) {
            super(userInterface);
            this.fakeComputerPlayer = fakeComputerPlayer;
        }

        @Override
        public Player createPlayer1(GameMode gameMode) {
            return new FakeHumanPlayer(fakeConsoleUI, X);
        }

        @Override
        public Player createPlayer2(GameMode gameMode) {
            return fakeComputerPlayer;
        }
    }

    private class PlayerFactoryWithTwoFakeHumanPlayers extends PlayerFactory {
        private final FakeHumanPlayer fakePlayer1;
        private final FakeHumanPlayer fakePlayer2;

        public PlayerFactoryWithTwoFakeHumanPlayers(ConsoleUi userInterface, FakeHumanPlayer player1, FakeHumanPlayer player2) {
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