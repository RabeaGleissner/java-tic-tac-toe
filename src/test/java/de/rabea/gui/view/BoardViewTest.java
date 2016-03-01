package de.rabea.gui.view;

import de.rabea.game.Board;
import de.rabea.game.GameRunner;
import de.rabea.player.GuiPlayer;
import de.rabea.gui.JavaFXUi;
import de.rabea.gui.ViewUpdater;
import de.rabea.gui.clickhandler.EmptyCellClickHandler;
import de.rabea.gui.clickhandler.FullCellClickHandler;
import de.rabea.player.PlayerFactory;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.X;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardViewTest {
    private GameRunner gameRunner;

    @Before
    public void setUp() {
        new JFXPanel();
        gameRunner = new GameRunner(new JavaFXUi(new ViewUpdater(new Scene(new GridPane()))),
                new PlayerFactory(null));
    }

    @Test
    public void threeByThreeBoardViewHasNineElements() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), gameRunner, board),
                new FullCellClickHandlerStub());
        Parent gridPane = boardView.draw(board, false);

        assertEquals(9, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void fourByFourBoardViewHasSixteenElements() {
        Board board = new Board(4);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), gameRunner, board),
                new FullCellClickHandlerStub());
        Parent gridPane = boardView.draw(board, false);

        assertEquals(16, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void allElementsOnEmptyBoardAreActiveButtons() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), gameRunner, board),
                new FullCellClickHandlerStub());
        Parent gridPane = boardView.draw(board, false);

        for (Node node : gridPane.getChildrenUnmodifiable()) {
            assertTrue(isActiveButton(node));
        }
    }

    @Test
    public void disabledButtonsContainCorrespondingPlayerMark() {
        Board board = new Board(3);
        Board nextBoard = board.placeMark(2, X);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), gameRunner, board),
                new FullCellClickHandlerStub());
        Parent node = boardView.draw(nextBoard, false);

        assertEquals("X", findDisabledButton(node, 2).getText());
    }

    @Test
    public void addsPositionInUseWarningWhenClickedPositionIsInUse() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), gameRunner, board),
                new FullCellClickHandlerStub());
        Parent node = boardView.draw(board, true);

        int numberOfElements = node.getChildrenUnmodifiable().size();
        String id = node.getChildrenUnmodifiable().get(numberOfElements-1).getId();

        assertEquals("positionInUseWarning", id);
    }

    @Test
    public void reactsToAClickByUpdatingPlayerWithPositionAndDisplayingBoard() {
        Board board = new Board(3);
        GameRunnerSpy gameRunnerSpy = new GameRunnerSpy();
        GuiPlayer guiPlayer = new GuiPlayer(X);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(guiPlayer, gameRunnerSpy, board),
                new FullCellClickHandlerStub());
        findActiveButton(boardView.draw(board, false), 7).fire();

        assertEquals(7, guiPlayer.getPosition(board));
        assertTrue(gameRunnerSpy.displayBoard);
    }

    private class FullCellClickHandlerStub extends FullCellClickHandler {

        public FullCellClickHandlerStub() {
            super(null, null, null, null);
        }

        @Override
        public void action(String position) {

        }
    }

    private class GameRunnerSpy extends GameRunner {

        private boolean displayBoard = false;

        public GameRunnerSpy() {
            super(null, null);
        }

        @Override
        public void playOneRound(Board board) {
            displayBoard = true;
        }
    }

    private Button findActiveButton(Parent node, int position) {
        Node target = node.getChildrenUnmodifiable().get(position);
        if(target instanceof Button  && target.getStyleClass().get(1).equals("active-button")) {
            return (Button) target;
        }

        throw new RuntimeException("Did not find an active button on position " + position);
    }

    private Button findDisabledButton(Parent node, int position) {
        Node target = node.getChildrenUnmodifiable().get(position);
        if(target instanceof Button && target.getStyleClass().get(1).equals("disabled-button")) {
            return (Button) target;
        }

        throw new RuntimeException("Did not find a disabled button on position " + position);
    }

    private boolean isActiveButton(Node node) {
        if (node instanceof Button && node.getStyleClass().get(1).equals("active-button")) {
            return true;
        }

        throw new RuntimeException("Node is not an active button");
    }
}