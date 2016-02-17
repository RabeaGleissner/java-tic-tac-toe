package de.rabea.gui;

public class GuiPlayer {

    private int clicked;

    public int clickedPosition() {
        return clicked;
    }

    public void click(int clicked) {
        this.clicked = clicked;
    }
}
