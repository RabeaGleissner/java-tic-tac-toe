package de.rabea.console;

import de.rabea.game.Board;
import de.rabea.game.GameMode;
import de.rabea.game.Mark;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeConsoleUserInterface extends ConsoleUi {

    private boolean positionUnavailableWarningWasCalled = false;
    private final List<String> moves = new LinkedList<>();
    public int countAnnounceGameEndCalls = 0;
    private final List<GameMode> gameModes = new LinkedList<>();
    private final List<String> replayChoices = new LinkedList<>();
    private final List<Integer> dimensions = new LinkedList<>();

    public FakeConsoleUserInterface() {
        super(new FakeConsole(), new PrettyBoardPainter());
    }

    @Override
    public void greet() {

    }

    @Override
    public String readUserInput() {
        return moves.remove(0);
    }

    @Override
    public int getBoardDimensionFromUser() {
        return dimensions.remove(0);
    }

    public void setBoardDimensions(Integer... selectedDimensions) {
        dimensions.addAll(Arrays.asList(selectedDimensions));
    }

    @Override
    public int getFormattedUserPosition(String userInput) {
        int position = Integer.parseInt(userInput);
        position --;
        return position;
    }

    @Override
    public void askForPosition() {
    }

    @Override
    public void announceGameEnd(Mark lastPlayedMark, boolean winner) {
        countAnnounceGameEndCalls++;
    }

    @Override
    public boolean playAgain() {
        return replayChoices.remove(0).equals("yes");
    }

    @Override
    public void positionUnavailableWarning(Board board) {
        positionUnavailableWarningWasCalled = true;
    }

    @Override
    public GameMode getGameModeFromUser() {
        return gameModes.remove(0);
    }

    public void setGameMode (GameMode... modes) {
        gameModes.addAll(Arrays.asList(modes));
    }

    public void choosePositions(String... userChoices) {
        moves.addAll(Arrays.asList(userChoices));
    }

    public void setReplayChoice(String... userChoice) {
        replayChoices.addAll(Arrays.asList(userChoice));
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

    public boolean wasPositionUnavailableWarningCalled() {
        return positionUnavailableWarningWasCalled;
    }
}
