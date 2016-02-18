package de.rabea.gui;

import de.rabea.game.Mark;

import static de.rabea.game.Mark.*;

public class GuiPlayer {

    private int position = -1;
    private GuiGame guiGame;
    private Mark currentMark = X;

    public GuiPlayer(GuiGame guiGame) {
        this.guiGame = guiGame;
    }

    public int clickedPosition() {
        return position;
    }

    public void click(int clicked) {
        guiGame.playRound(clicked, currentMark, this);
        this.position = clicked;
        switchMark();
    }

    public void switchMark() {
        if (currentMark == X) {
            currentMark = O;
        } else {
            currentMark = X;
        }
    }

    public Mark getCurrentMark() {
        return currentMark;

    }
}
