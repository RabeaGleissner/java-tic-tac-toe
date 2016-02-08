package de.rabea.game;

import static de.rabea.game.Mark.EMPTY;

public class Line {
    private Mark[] line;

    public Line(Mark... marks) {
        line = marks;
    }

    public boolean hasWinner() {
        return (allX() || allO()) && line[0]!= EMPTY;
    }

    private boolean allO() {
        boolean allSame = true;
        for (Mark cell : line) {
            allSame = allSame && (cell == Mark.O);
        }
        return allSame;
    }

    public Mark firstMarkInLine() {
        return line[0];
    }

    private boolean allX() {
        boolean allSame = true;
        for (Mark cell : line) {
            allSame = allSame && (cell == Mark.X);
        }
        return allSame;
    }
}
