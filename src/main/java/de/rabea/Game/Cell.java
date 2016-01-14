package de.rabea.game;

public enum Cell {
    X,
    O,
    EMPTY;

    public Mark convertToMark() {
        Mark mark;
        if (this == Cell.X) {
            mark = Mark.X;
        } else {
            mark = Mark.O;
        }
        return mark;
    }
}
