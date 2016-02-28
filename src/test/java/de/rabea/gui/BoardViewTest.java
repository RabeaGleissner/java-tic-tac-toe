package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.player.PlayerFactory;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.Test;

import static de.rabea.game.Mark.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardViewTest {
    private ViewUpdater viewUpdater;
    private GuiApp guiApp;

    @Before
    public void setUp() {
        new JFXPanel();
        viewUpdater = new ViewUpdater(new Scene(new GridPane()));
        guiApp = new GuiApp(viewUpdater, new PlayerFactory(null));
    }

    @Test
    public void threeByThreeBoardHasNineChildren() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), guiApp, board),
                new FullCellClickHandlerStub());
        Parent gridPane = boardView.draw(board, false);

        assertEquals(9, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void fourByFourBoardHasSixteenChildren() {
        Board board = new Board(4);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), guiApp, board),
                new FullCellClickHandlerStub());
        Parent gridPane = boardView.draw(board, false);

        assertEquals(16, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void allElementsOnEmptyBoardAreActiveButtons() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), guiApp, board),
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
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), guiApp, board),
                new FullCellClickHandlerStub());
        Parent node = boardView.draw(nextBoard, false);

        assertEquals("X", findDisabledButton(node, 2).getText());
    }

    @Test
    public void addsPositionInUseWarningWhenPositionIsInUse() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(new GuiPlayer(X), guiApp, board),
                new FullCellClickHandlerStub());
        Parent node = boardView.draw(board, true);

        int numberOfElements = node.getChildrenUnmodifiable().size();
        String id = node.getChildrenUnmodifiable().get(numberOfElements-1).getId();

        assertEquals("positionInUseWarning", id);
    }

    @Test
    public void reactsToAClick() {
        Board board = new Board(3);
        GuiAppSpy guiAppSpy = new GuiAppSpy();
        GuiPlayer guiPlayer = new GuiPlayer(X);
        BoardView boardView = new BoardView(new EmptyCellClickHandler(guiPlayer, guiAppSpy, board), new FullCellClickHandlerStub());
        Parent drawnBoard = boardView.draw(board, false);
        Button button = findActiveButton(drawnBoard, 7);
        button.fire();

        assertEquals(7, guiPlayer.getPosition(board));
        assertTrue(guiAppSpy.displayBoard);
    }

    private class FullCellClickHandlerStub extends FullCellClickHandler {

        public FullCellClickHandlerStub() {
            super(null, null, null, null);
        }

        @Override
        public void action(String position) {

        }
    }

    private class GuiAppSpy extends GuiApp {

        private boolean displayBoard = false;

        public GuiAppSpy() {
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