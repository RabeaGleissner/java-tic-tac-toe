package de.rabea.game;

public enum Mark {
    X,
    O,
    EMPTY;

    public Mark switchMark(Mark mark) {
        if (mark == Mark.X) {
            return Mark.O;
        } else {
            return Mark.X;
        }
    }
}
