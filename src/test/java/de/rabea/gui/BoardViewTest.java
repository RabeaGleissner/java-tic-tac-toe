package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        guiApp = new GuiApp(viewUpdater);
    }

    @Test
    public void threeByThreeBoardHasNineChildren() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new BoardClickHandler(new GuiPlayer(X), guiApp, board));
        Parent gridPane = boardView.draw(board);

        assertEquals(9, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void fourByFourBoardHasSixteenChildren() {
        Board board = new Board(4);
        BoardView boardView = new BoardView(new BoardClickHandler(new GuiPlayer(X), guiApp, board));
        Parent gridPane = boardView.draw(board);

        assertEquals(16, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void allElementsOnEmptyBoardAreButtons() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new BoardClickHandler(new GuiPlayer(X), guiApp, board));
        Parent gridPane = boardView.draw(board);

        for (Node node : gridPane.getChildrenUnmodifiable()) {
            assertTrue(isButton(node));
        }
    }

    @Test
    public void labelsContainCorrespondingPlayerMark() {
        Board board = new Board(3);
        Board nextBoard = board.placeMark(2, X);
        BoardView boardView = new BoardView(new BoardClickHandler(new GuiPlayer(X), guiApp, board));
        Parent node = boardView.draw(nextBoard);

        assertEquals("X", findLabel(node, 2).getText());
    }

    @Test
    public void reactsToAClick() {
        Board board = new Board(3);
        GuiAppSpy guiAppSpy = new GuiAppSpy(viewUpdater);
        GuiPlayer guiPlayer = new GuiPlayer(X);
        BoardView boardView = new BoardView(new BoardClickHandler(guiPlayer, guiAppSpy, board));
        Parent drawnBoard = boardView.draw(board);
        Button button = findButton(drawnBoard, 7);
        button.fire();

        assertEquals(7, guiPlayer.getPosition(board));
        assertTrue(guiAppSpy.displayBoard);
    }

    private class GuiAppSpy extends GuiApp {

        private boolean displayBoard = false;

        public GuiAppSpy(ViewUpdater viewUpdater) {
            super(viewUpdater);
        }

        @Override
        public void playOneRound(Board board, GuiPlayer player) {
            displayBoard = true;
        }
    }

    private Button findButton(Parent node, int position) {
        Node target = node.getChildrenUnmodifiable().get(position);
        if(target instanceof Button) {
            return (Button) target;
        }

        throw new RuntimeException("Did not find a button on position " + position);
    }

    private Label findLabel(Parent node, int position) {
        Node target = node.getChildrenUnmodifiable().get(position);
        if(target instanceof Label) {
            return (Label) target;
        }

        throw new RuntimeException("Did not find a label on position " + position);
    }

    private boolean isButton(Node node) {
        return node instanceof Button;
    }
}