package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class NextGuiPlayer extends Player {

    private final ClickCarrier carrier;

    public NextGuiPlayer(Mark mark, ClickCarrier carrier) {
        super(mark);
        this.carrier = carrier;
    }

    public boolean hasMove() {
        return carrier.getMove() != -1;
    }

    @Override
    public int getPosition(Board board) {
        return carrier.getMove();
    }
}
