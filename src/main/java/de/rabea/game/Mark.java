package de.rabea.game;

public enum Mark {
    X,
    O,
    EMPTY;

    public Mark switchMark(Mark mark) {
        if (mark == X) {
            return O;
        } else {
            return X;
        }
    }
}
