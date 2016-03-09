package de.rabea.game;

public abstract class Player {
    protected final Mark mark;

    protected Player(Mark mark) {
        this.mark = mark;
    }

    public Mark mark() {
        return mark;
    }
    public abstract int getMove(Board board);
    public abstract boolean hasMove();
    public abstract Board makeMove(Board board);
}
