package de.rabea.game;

import org.junit.Test;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;
import static junit.framework.TestCase.assertEquals;

public class MarkTest {
    @Test
    public void switchesMarkXtoO() {
        Mark mark = O;
        assertEquals(mark, mark.switchMark(X));
    }

    @Test
    public void switchesMarkOToX() {
        Mark mark = X;
        assertEquals(mark, mark.switchMark(O));
    }
}