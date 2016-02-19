package de.rabea.gui;

public class BoardClickHandler implements ClickHandler {
    private ClickCarrier clickCarrier;

    public BoardClickHandler(ClickCarrier clickCarrier) {
        this.clickCarrier = clickCarrier;
    }


    @Override
    public void action(String position) {
        clickCarrier.addMove(convertToInteger(position));
    }

    private int convertToInteger(String position) {
        return Integer.parseInt(position);
    }
}
