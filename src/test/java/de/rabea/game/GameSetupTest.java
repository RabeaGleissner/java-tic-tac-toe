package de.rabea.game;

import de.rabea.player.FakeComputerPlayer;
import de.rabea.player.HumanPlayer;
import de.rabea.player.PlayerFactory;
import de.rabea.ui.FakeUserInterface;
import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameSetupTest {
    FakeUserInterface fakeUserInterface;
    PlayerFactory playerFactory;

    @Before
    public void setup() {
        fakeUserInterface = new FakeUserInterface();
        playerFactory = new PlayerFactory(fakeUserInterface);
    }

    @Test
    public void greetsUserWhenFirstSettingUpGame() {
        fakeConsoleInputForOneHvH3x3Game();
        GameSetup gameSetup = new GameSetup(fakeUserInterface, playerFactory);
        gameSetup.start();
        assertTrue(fakeUserInterface.greetUserWasCalled);
    }

    @Test
    public void createsTwoHumanPlayersWhenRequested() {
        fakeConsoleInputForOneHvH3x3Game();
        GameSetup gameSetup = new GameSetup(fakeUserInterface, playerFactory);
        gameSetup.start();
        assertTrue(gameSetup.getPlayer() instanceof HumanPlayer);
        assertTrue(gameSetup.getOpponent() instanceof HumanPlayer);
    }

    @Test
    public void playsHuman3x3GameTwice() {
        fakeConsoleInputForTwoHvH3x3Games();
        GameSetup gameSetup = new GameSetup(fakeUserInterface, playerFactory);
        gameSetup.setUpGameAndPlay();
        assertTrue(fakeUserInterface.wasAskForPositionCalled());
        assertEquals(2, fakeUserInterface.announceWinnerCalled());
    }

    @Test
    public void createsHumanAndComputerPlayer() {
        fakeConsoleInputForOneHvC3x3Game();
        GameSetup gameSetup = new GameSetup(fakeUserInterface,
                new FakePlayerFactory(fakeUserInterface, createFakeComputerPlayerWithInput()));
        gameSetup.start();
        assertTrue(gameSetup.getPlayer() instanceof HumanPlayer);
        assertTrue(gameSetup.getOpponent() instanceof FakeComputerPlayer);
    }

    private FakeComputerPlayer createFakeComputerPlayerWithInput() {
        FakeComputerPlayer fakeComputerPlayer = new FakeComputerPlayer(O);
        fakeComputerPlayer.giveNumbers(6, 7);
        return fakeComputerPlayer;
    }

    private class FakePlayerFactory extends PlayerFactory {
        private FakeComputerPlayer fakeComputerPlayer;

        public FakePlayerFactory(UserInterface userInterface, FakeComputerPlayer fakeComputerPlayer) {
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

    private void fakeConsoleInputForOneHvH3x3Game() {
        chooseHumanVsHumanGame();
        choose3x3BoardSize();
        makeMoves();
        doNotPlayAgain();
    }

    private void fakeConsoleInputForTwoHvH3x3Games() {
        chooseHumanVsHumanGame();
        choose3x3BoardSize();
        makeMoves();
        fakeUserInterface.replayChoice("yes");
        fakeConsoleInputForOneHvH3x3Game();
    }

    private void fakeConsoleInputForOneHvC3x3Game() {
        fakeUserInterface.chooseGameType("Human vs Computer");
        choose3x3BoardSize();
        fakeUserInterface.choosePositions("1", "2", "3");
        doNotPlayAgain();
    }

    private void doNotPlayAgain() {
        fakeUserInterface.replayChoice("no");
    }

    private void makeMoves() {
        fakeUserInterface.choosePositions("1", "7", "2", "4", "3");
    }

    private void choose3x3BoardSize() {
        fakeUserInterface.chooseBoardSize("3x3");
    }

    private void chooseHumanVsHumanGame() {
        fakeUserInterface.chooseGameType("Human vs Human");
    }
}