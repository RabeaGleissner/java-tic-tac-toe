package de.rabea.game;

public abstract class Player {
    protected Mark mark;

    public Player(Mark mark) {
        this.mark = mark;
    }

    public Mark mark() {
        return mark;
    }
    public abstract int getPosition(Board board);
}
