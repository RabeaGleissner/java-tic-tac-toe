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

    public FakeUserInterface() {
        super(new FakeConsole());
    }

    @Override
    public void greet() {
        greetUserWasCalled = true;
    }

    @Override
    public Integer returnUserChoiceForPosition(Cell[] cells) {
        askForPositionWasCalled = true;
        UserInterface userInterface = new UserInterface(new FakeConsole());
        Board board = new Board();
        int position = userInterface.formatUserChoiceForPosition(moves.remove(0), board.returnCells());
        return position;
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
}
