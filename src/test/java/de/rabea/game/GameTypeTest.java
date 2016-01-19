package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static org.junit.Assert.assertTrue;

public class GameTypeTest {

    FakeUserInterface fakeUserInterface;
    FakeComputerPlayer fakeComputerPlayer;
    GameType gameType;
    GameManager gameManager;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        fakeComputerPlayer = new FakeComputerPlayer(O);
        gameManager = new GameManager(fakeUserInterface);
        gameType = new GameType(fakeUserInterface, gameManager);
    }

    @Test
    public void itSetsUpTheFirstGameAsAHvHGame() {
        fakeUserInterface.provideConsoleInput("2");
        Game game = gameType.createGame();
        assertTrue(game != null);
    }

    @Test
    public void itCreatesAHumanVsHumanGame() {
        fakeUserInterface.provideConsoleInput("1");
        Game game = gameType.createGame();
        assertTrue(game != null);
    }
}

