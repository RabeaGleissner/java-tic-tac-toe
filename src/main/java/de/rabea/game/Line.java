package de.rabea.game;

import static de.rabea.game.Cell.EMPTY;

public class Line {
    private final Cell first;
    private final Cell second;
    private final Cell third;

    public Line(Cell first, Cell second, Cell third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public boolean hasWinner() {
        return allSame() && first != EMPTY;
    }

    private boolean allSame() {
        return first == second && second == third;
    }
}
