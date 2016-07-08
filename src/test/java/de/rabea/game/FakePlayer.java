package de.rabea.game;

import java.util.ArrayList;

public class FakePlayer extends Player {

    private final Mark mark;
    private final ArrayList<Integer> moves;

    protected FakePlayer(Mark mark, ArrayList<Integer> moves) {
        super(mark);
        this.mark = mark;
        this.moves = moves;
    }

    @Override
    public int getMove(Board board) {
        return 0;
    }

    @Override
    public boolean hasMove() {
        return moves.size() != 0;
    }

    @Override
    public Board makeMove(Board board) {
        return board.placeMark(moves.remove(0), mark);
    }
}
