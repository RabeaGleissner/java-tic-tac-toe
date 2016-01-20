package de.rabea.game;

import static de.rabea.game.Mark.EMPTY;

public class Line {
    private final Mark first;
    private final Mark second;
    private final Mark third;

    public Line(Mark first, Mark second, Mark third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public boolean hasWinner() {
        return allSame() && first != EMPTY;
    }

    public Mark firstMarkInLine() {
        return first;
    }

    private boolean allSame() {
        return first == second && second == third;
    }
}
