package de.rabea.gui;

public class ClickCarrier {

    private int clicked;

    public int whatWasClicked() {
        return clicked;
    }

    public void click(int clicked) {
        this.clicked = clicked;
    }
}
