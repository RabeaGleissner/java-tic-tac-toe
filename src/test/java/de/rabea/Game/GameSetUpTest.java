package de.rabea.game;

import de.rabea.ui.FakeUserInterface;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameSetUpTest {

    FakeUserInterface fakeUserInterface;
    FakeComputerPlayer fakeComputerPlayer;
    GameSetUp gameSetUp;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        fakeComputerPlayer = new FakeComputerPlayer(new FakeRandomNumberCalculator(), Mark.O);
        gameSetUp = new GameSetUp(fakeUserInterface);
    }

    @Test
    public void itSetsUpTheFirstGameAsAHvHGame() {
        fakeUserInterface.provideConsoleInput("2", "1", "7", "3", "4", "2", "n");
        gameSetUp.setUpFirstGame();
        assertTrue(fakeUserInterface.wasGreetUserCalled());
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void itCreatesAComputerPlayer() {
        ComputerPlayer computerPlayer = gameSetUp.createNewComputerPlayer();
        assertTrue(computerPlayer instanceof ComputerPlayer);

    }

    @Test
    public void itSetsUpAHvCGame() {
        fakeUserInterface.provideConsoleInput("1", "1", "4", "7", "n");
        fakeComputerPlayer.giveNumbers(1, 2);
        GameSetUpWithFakeComputerPlayer gameSetUp = new GameSetUpWithFakeComputerPlayer();
        gameSetUp.setUpGame();
        assertEquals(1, fakeUserInterface.announceWinnerCalled());
    }

    public class GameSetUpWithFakeComputerPlayer extends GameSetUp {
        public GameSetUpWithFakeComputerPlayer() {
            super(fakeUserInterface);
        }

        @Override
        public ComputerPlayer createNewComputerPlayer() {
            FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(new RandomNumberCalculator(), Mark.O);
            fakeComputerPlayer.giveNumbers(0, 3, 6);
            return fakeComputerPlayer;
        }
    }
}