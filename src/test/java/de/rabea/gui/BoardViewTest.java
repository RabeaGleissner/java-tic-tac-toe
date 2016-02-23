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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardViewTest {
    ViewUpdater viewUpdater;
    GuiApp guiApp;

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
        viewUpdater = new ViewUpdater(new Scene(new GridPane()));
        guiApp = new GuiApp(viewUpdater);

    }

    @Test
    public void threeByThreeBoardHasNineChildren() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new BoardClickHandler(new ClickCarrier(), guiApp));
        Parent gridPane = boardView.draw(board, guiApp);

        assertEquals(9, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void fourByFourBoardHasSixteenChildren() {
        Board board = new Board(4);
        BoardView boardView = new BoardView(new BoardClickHandler(new ClickCarrier(), guiApp));
        Parent gridPane = boardView.draw(board, guiApp);

        assertEquals(16, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void allElementsOnEmptyBoardAreButtons() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(new BoardClickHandler(new ClickCarrier(), guiApp));
        Parent gridPane = boardView.draw(board, guiApp);

        for (Node node : gridPane.getChildrenUnmodifiable()) {
            assertTrue(isButton(node));
        }
    }

    @Test
    public void labelsContainCorrespondingPlayerMark() {
        Board board = new Board(3);
        board.placeMarkOnExistingBoard(2, Mark.X);

        BoardView boardView = new BoardView(new BoardClickHandler(new ClickCarrier(), guiApp));
        Parent node = boardView.draw(board, guiApp);
        assertEquals("X", findLabel(node, 2).getText());
    }

    @Test
    public void reactsToAClick() {
        Board board = new Board(3);
        GuiAppSpy guiAppSpy = new GuiAppSpy(viewUpdater);

        ClickCarrier carrier = new ClickCarrier();
        BoardView boardView = new BoardView(new BoardClickHandler(carrier, guiAppSpy));
        Parent drawnBoard = boardView.draw(board, guiAppSpy);
        Button button = findButton(drawnBoard, 7);
        button.fire();

        assertEquals(7, carrier.getMove());
        assertTrue(guiAppSpy.displayBoard);
    }

    private class GuiAppSpy extends GuiApp {

        private boolean displayBoard = false;

        public GuiAppSpy(ViewUpdater viewUpdater) {
            super(viewUpdater);
        }

        @Override
        public void displayBoard() {
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