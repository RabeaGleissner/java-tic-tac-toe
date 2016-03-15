package de.rabea.player;

public enum FakePlayerMove {
    TOP_LEFT,
    TOP_CENTRE,
    TOP_RIGHT,
    CENTRE_LEFT,
    CENTRE_CENTRE,
    CENTRE_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_CENTER,
    BOTTOM_RIGHT;

    public Integer convertToInteger() {
        return (Integer) this.ordinal();
    }
}
