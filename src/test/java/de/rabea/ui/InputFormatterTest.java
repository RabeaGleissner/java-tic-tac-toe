package de.rabea.ui;

import de.rabea.game.Replay;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputFormatterTest {

    private InputFormatter inputFormatter;

    @Before
    public void setup() {
        inputFormatter = new InputFormatter();
    }

    @Test
    public void itConvertsUserReplayChoiceYesToEnum() {
        assertEquals(Replay.YES, inputFormatter.formatForReplayOption("y"));
    }

    @Test
    public void itConvertsUserReplayChoiceNoToEnum() {
        assertEquals(Replay.NO, inputFormatter.formatForReplayOption("n"));
    }

    @Test
    public void itReturnsNullWhenUserGivesBadInputForReplayChoice() {
        assertEquals(null, inputFormatter.formatForReplayOption("h"));
    }

    @Test
    public void itReturnsTrueIfStringCanBeConvertedToInt() {
        assertEquals(true, inputFormatter.isInteger("1"));
    }

    @Test
    public void itReturnsFalseIfStringCannotBeConvertedToInt() {
        assertEquals(false, inputFormatter.isInteger("hello"));
    }

    @Test
    public void itSubtractsOneFromAGivenInteger() {
        assertEquals(4, inputFormatter.subtractOneToMatchArrayIndex(5));
    }
}