package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Cell;
import de.rabea.game.GameMode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeUserInterface extends UserInterface {

    public boolean greetUserWasCalled = false;
    public boolean askForPositionWasCalled = false;
    private boolean positionUnavailableWarningWasCalled = false;
    private List<String> moves = new LinkedList<String>();
    private int countAnnounceGameEndCalls = 0;

    public FakeUserInterface() {
        super(new FakeConsole());
    }

    @Override
    public void greet() {
        greetUserWasCalled = true;
    }

    @Override
    public String readUserInput() {
        askForPositionWasCalled = true;
        String userInput = moves.remove(0);
        return userInput;
    }

    @Override
    public int getFormattedUserPosition(String userInput) {
        int position = Integer.parseInt(userInput);
        position --;
        return position;
    }

    @Override
    public void askForPosition() {
        askForPositionWasCalled = true;
    }

    @Override
    public void announceGameEnd(Cell lastPlayedMark, boolean winner) {
        countAnnounceGameEndCalls++;
    }

    @Override
    public boolean playAgain() {
        return moves.remove(0).equals("y");
    }

    @Override
    public void positionUnavailableWarning() {
        positionUnavailableWarningWasCalled = true;
    }

    @Override
    public GameMode chooseGameMode() {
        if (moves.remove(0).equals("1")) {
            return GameMode.HvC;
        } else {
            return GameMode.HvH;
        }

    }

    public void provideConsoleInput(String... userChoices) {
        moves.addAll(Arrays.asList(userChoices));
    }

    public boolean wasGreetUserCalled() {
        return greetUserWasCalled;
    }

    public boolean wasAskForPositionCalled() {
        return askForPositionWasCalled;
    }

    public int announceWinnerCalled() {
        return countAnnounceGameEndCalls;
    }

    public boolean wasPositionUnavailableWarningCalled() {
        return positionUnavailableWarningWasCalled;
    }
}
