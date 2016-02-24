package de.rabea.ui;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeUserInterface extends ConsoleUi {

    private boolean askForPositionWasCalled = false;
    private boolean positionUnavailableWarningWasCalled = false;
    private final List<String> moves = new LinkedList<>();
    private int countAnnounceGameEndCalls = 0;

    public FakeUserInterface() {
        super(new FakeConsole(), new PrettyBoardPainter());
    }

    @Override
    public void greet() {
    }

    @Override
    public String readUserInput() {
        askForPositionWasCalled = true;
        return moves.remove(0);
    }

    @Override
    public int getBoardDimensionFromUser() {
        String input = moves.remove(0);
        return Integer.parseInt(input);
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
    public void announceGameEnd(Mark lastPlayedMark, boolean winner) {
        countAnnounceGameEndCalls++;
    }

    @Override
    public boolean playAgain() {
        return moves.remove(0).equals("y");
    }

    @Override
    public void positionUnavailableWarning(Board board) {
        positionUnavailableWarningWasCalled = true;
    }

    @Override
    public GameMode getGameModeFromUser() {
        String userChoice = moves.remove(0);
        switch (userChoice) {
            case "1":
                return GameMode.HumanVsHuman;
            case "2":
                return GameMode.HumanVsComputer;
            case "3":
                return GameMode.ComputerVsHuman;
            default:
                throw new RuntimeException("did not recognise choice for game mode");
        }
    }

    public void fakeConsoleInputForOneHvH3x3Game() {
        chooseGameType("Human vs Human");
        chooseBoardSize("3x3");
        choosePositions("1", "7", "2", "4", "3");
        replayChoice("no");
    }

    public void fakeConsoleInputForTwoHvH3x3Games() {
        chooseGameType("Human vs Human");
        chooseBoardSize("3x3");
        choosePositions("1", "7", "2", "4", "3");
        replayChoice("yes");
        fakeConsoleInputForOneHvH3x3Game();
    }

    public void fakeConsoleInputForOneHvC3x3Game() {
        chooseGameType("Human vs Computer");
        chooseBoardSize("3x3");
        choosePositions("1", "2", "3");
        replayChoice("no");
    }

    public void choosePositions(String... userChoices) {
        moves.addAll(Arrays.asList(userChoices));
    }

    private void chooseBoardSize(String choice) {
        if (choice.equals("3x3")) {
            moves.add("3");
        } else if (choice.equals("4x4")) {
            moves.add("4");
        } else {
            throw new RuntimeException("board size choice not available");
        }
    }

    private void chooseGameType(String choice) {
        if (choice.equals("Human vs Human")) {
            moves.add("1");
        } else if (choice.equals("Human vs Computer")) {
            moves.add("2");
        } else {
            throw new RuntimeException("game type choice not implemented");
        }
    }

    public void replayChoice(String userChoice) {
        if (userChoice.equals("no")) {
            moves.add("n");
        } else if (userChoice.equals("yes")) {
            moves.add("y");
        } else {
            throw new RuntimeException("did not recognise replay choice");
        }
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
