package de.rabea;

public class Board {

    private String[] cells;

    public Board() {
        String emptySlot = "_";
        this.cells = new String[] {emptySlot, emptySlot, emptySlot,
                emptySlot, emptySlot, emptySlot,
                emptySlot, emptySlot, emptySlot};
    }

    public String[] returnCells() {
        return cells;
    }
}
