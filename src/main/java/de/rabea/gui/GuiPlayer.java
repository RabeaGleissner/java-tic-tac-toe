package de.rabea.gui;

public class GuiPlayer {

    private int position = -1;
    private GuiGame guiGame;

    public GuiPlayer(GuiGame guiGame) {
        this.guiGame = guiGame;
    }

    public int clickedPosition() {
        return position;
    }

    public void click(int clicked) {
        guiGame.playRound(clicked);
        this.position = clicked;
    }
}
