package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardViewTest {

    @Before
    public void setUp() throws Exception {
        new JFXPanel();
    }

    @Test
    public void threeByThreeBoardHasNineChildren() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(board, new GuiPlayer(new GuiGame(board)));
        Parent gridPane = boardView.draw();

        assertEquals(9, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void fourByFourBoardHasSixteenChildren() {
        Board board = new Board(4);
        BoardView boardView = new BoardView(board, new GuiPlayer(new GuiGame(board)));
        Parent gridPane = boardView.draw();

        assertEquals(16, gridPane.getChildrenUnmodifiable().size());
    }

    @Test
    public void allElementsOnEmptyBoardAreButtons() {
        Board board = new Board(3);
        BoardView boardView = new BoardView(board, new GuiPlayer(new GuiGame(board)));
        Parent gridPane = boardView.draw();

        for (Node node : gridPane.getChildrenUnmodifiable()) {
            assertTrue(isButton(node));
        }
    }

    @Test
    public void labelsContainCorrespondingPlayerMark() {
        Board board = new Board(3);
        board.placeMarkOnExistingBoard(2, Mark.X);

        BoardView boardView = new BoardView(board, new GuiPlayer(new GuiGame(board)));
        Parent node = boardView.draw();
        assertEquals("X", findLabel(node, 2).getText());
    }

    @Test
    public void reactsToAClick() {
        Board board = new Board(3);
        GuiPlayer carrier = new GuiPlayer(new GuiGame(board));
        BoardView boardView = new BoardView(board, carrier);
        Parent drawnBoard = boardView.draw();
        Button button = findButton(drawnBoard, 7);
        button.fire();

        assertEquals(carrier.clickedPosition(), 7);
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