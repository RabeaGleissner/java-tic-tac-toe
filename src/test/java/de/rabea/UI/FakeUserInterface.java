package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.Cell;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeUserInterface extends UserInterface {

    public boolean greetUserWasCalled = false;
    public boolean askForPositionWasCalled = false;
    private List<String> moves = new LinkedList<String>();
    private int countAnnounceWinnerCalls = 0;

    public FakeUserInterface() {
        super(new FakeConsole());
    }

    @Override
    public void greet() {
        greetUserWasCalled = true;
    }

    @Override
    public Integer returnPlayersChosenPosition(Board board) {
        askForPositionWasCalled = true;
        UserInterface userInterface = new UserInterface(new FakeConsole());
        int position = Integer.parseInt(moves.remove(0));
        return position;
    }

    @Override
    public void announceGameEnd(Cell lastPlayedMark, boolean winner) {
        countAnnounceWinnerCalls ++;
    }

    @Override
    public boolean playAgain() {
        return moves.remove(0).equals("y");
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
        return countAnnounceWinnerCalls;
    }
}
