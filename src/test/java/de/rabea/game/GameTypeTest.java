package de.rabea.game;

import de.rabea.player.FakeComputerPlayer;
import de.rabea.ui.FakeConsole;
import de.rabea.ui.FakeUserInterface;
import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.assertTrue;

public class GameTypeTest {

    FakeUserInterface fakeUserInterface;
    FakeComputerPlayer fakeComputerPlayer;
    GameManager gameManager;
    Console fakeConsole;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        fakeComputerPlayer = new FakeComputerPlayer(O);
        gameManager = new GameManager(fakeUserInterface);
        fakeConsole = new FakeConsole();
    }

    @Test
    public void createsAHumanVsHumanGame() {
        GameType gameType = new GameType(new UIReturningHvH(fakeConsole), gameManager);
        assertTrue(gameType.createGame() != null);
    }

    @Test
    public void createsAHumanVsComputerGame() {
        GameType gameType = new GameType(new UIReturningHvC(fakeConsole), gameManager);
        assertTrue(gameType.createGame() != null);
    }

    @Test
    public void createsAComputerVsHumanGame() {
        GameType gameType = new GameType(new UIReturningCvH(fakeConsole), gameManager);
        assertTrue(gameType.createGame() != null);
    }

    @Test
    public void createsAComputerVsComputerGame() {
        GameType gameType = new GameType(new UIReturningCvC(fakeConsole), gameManager);
        assertTrue(gameType.createGame() != null);
    }

    private class UIReturningHvH extends UserInterface {
        public UIReturningHvH(Console console) {
            super(console);
        }

        @Override
        public GameMode getGameModeFromUser() {
            return GameMode.HumanVsHuman;
        }
    }

    private class UIReturningHvC extends UserInterface {
        public UIReturningHvC(Console console) {
            super(console);
        }

        @Override
        public GameMode getGameModeFromUser() {
            return GameMode.HumanVsComputer;
        }
    }

    private class UIReturningCvH extends UserInterface {
        public UIReturningCvH(Console console) {
            super(console);
        }

        @Override
        public GameMode getGameModeFromUser() {
            return GameMode.ComputerVsHuman;
        }
    }

    private class UIReturningCvC extends UserInterface {
        public UIReturningCvC(Console console) {
            super(console);
        }

        @Override
        public GameMode getGameModeFromUser() {
            return GameMode.ComputerVsComputer;
        }
    }
}

