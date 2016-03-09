package de.rabea.player;

import de.rabea.game.Board;
import de.rabea.game.Mark;
import de.rabea.game.Player;

public class GuiPlayer extends Player {

    private int position;
    private boolean moveAvailable;

    public GuiPlayer(Mark mark) {
        super(mark);
        this.moveAvailable = false;
    }

    @Override
    public boolean hasMove() {
        return this.moveAvailable;
    }

    @Override
    public Board makeMove(Board board) {
        return board.placeMark(getMove(board), this.mark);
    }

    @Override
    public int getMove(Board board) {
        if (moveAvailable) {
            this.moveAvailable = false;
            return position;
        } else {
            return -1;
        }
    }

    public void addMove(int position) {
        this.position = position;
        this.moveAvailable = true;
    }
}
