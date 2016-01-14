package de.rabea.game;

public enum Mark {
    X,
    O;

    public Cell convertToCell() {
        Cell cell;
        if (this == Mark.X) {
            cell = Cell.X;
        } else {
            cell = Cell.O;
        }
        return cell;
    }
}
