package de.rabea.game;

import org.junit.Test;

import static de.rabea.game.GameMode.*;
import static org.junit.Assert.assertTrue;

public class GameRunnerTest {

    @Test
    public void displaysGameModeOptions() {
        UserInterfaceSpy spy = new UserInterfaceSpy();
        FakePlayerFactory fakePlayerFactory = new FakePlayerFactory();
        GameRunner gameRunner = new GameRunner(spy, fakePlayerFactory);
        gameRunner.displayGameModeOptions();
        assertTrue(spy.gameModeOptionsWereDisplayed);
    }

    @Test
    public void asksForBoardSize() {
        UserInterfaceSpy spy = new UserInterfaceSpy();
        FakePlayerFactory fakePlayerFactory = new FakePlayerFactory();
        GameRunner gameRunner = new GameRunner(spy, fakePlayerFactory);
        gameRunner.setGameAndDisplayBoardSizeOptions(HumanVsHuman);
        assertTrue(spy.askedForBoardDimensions);
    }

    @Test
    public void playsGame() {
        GameSpy spy = new GameSpy();
        GameRunnerWithGameSpy gameRunner = new GameRunnerWithGameSpy(new UserInterfaceSpy(), new FakePlayerFactory(), spy);
        gameRunner.setGameAndDisplayBoardSizeOptions(HumanVsHuman);
        gameRunner.playWithFreshBoard(3);
        assertTrue(spy.playWasCalled);
    }

    @Test
    public void runsEntireGameCycleIncludingReplay() {
        UserInterfaceSpy spy = new UserInterfaceSpy();
        FakePlayerFactory fakePlayerFactory = new FakePlayerFactory();
        GameRunner gameRunner = new GameRunner(spy, fakePlayerFactory);
        gameRunner.setUpConsoleGameAndPlay();
        assertTrue(spy.askedForReplay);
    }

    private class GameRunnerWithGameSpy extends GameRunner {
        private final GameSpy spy;

        public GameRunnerWithGameSpy(UserInterface userInterface, PlayerFactory playerFactory, GameSpy spy) {
            super(userInterface, playerFactory);
            this.spy = spy;
        }

        @Override
        public Game createGame(GameMode gameMode) {
            return spy;
        }
    }

    private class GameSpy extends Game {
        public boolean playWasCalled = false;

        public GameSpy() {
            super(null, null, null);
        }

        @Override
        public void play(Board board) {
            playWasCalled = true;
        }
    }
}