package de.rabea.game;

public abstract class Player {
    protected Mark mark;

    protected Player(Mark mark) {
        this.mark = mark;
    }

    public Mark mark() {
        return mark;
    }
    public abstract int getPosition(Board board);
    public abstract boolean hasMove();


}
