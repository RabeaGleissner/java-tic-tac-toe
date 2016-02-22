package de.rabea.gui;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class GuiPlayer extends Player {

    private final ClickCarrier carrier;

    public GuiPlayer(Mark mark, ClickCarrier carrier) {
        super(mark);
        this.carrier = carrier;
    }

    @Override
    public boolean hasMove() {
        return carrier.isMoveAvailable();
    }

    @Override
    public int getPosition(Board board) {
        return carrier.getMove();
    }

    public ClickCarrier getCarrier() {
        return carrier;
    }
}
