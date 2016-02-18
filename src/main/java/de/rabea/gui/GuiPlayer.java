package de.rabea.gui;

public class GuiPlayer {

    private int position = -1;

    public int clickedPosition() {
        return position;
    }

    public void click(int clicked) {
        this.position = clicked;
    }


}
