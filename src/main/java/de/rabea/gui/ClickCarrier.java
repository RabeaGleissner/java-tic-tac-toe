package de.rabea.gui;

public class ClickCarrier {

    private int position ;
    private boolean moveAvailable;

    public ClickCarrier(int position) {
        this.position = position;
        this.moveAvailable = true;
    }

    public ClickCarrier() {
        this.moveAvailable = false;
    }

    public static ClickCarrier withMove(int position) {
        return new ClickCarrier(position);
    }

    public static ClickCarrier withNoData() {
        return new ClickCarrier();
    }

    public int getMove() {
        if (moveAvailable) {
            moveAvailable = false;
            return position;
        } else {
            return -1;
        }
    }

    public void addMove(int move) {
        System.out.println("move (in ClickCarrier) = " + move);
        this.position = move;
        moveAvailable = true;
    }
}
