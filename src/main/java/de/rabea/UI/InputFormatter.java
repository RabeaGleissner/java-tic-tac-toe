package de.rabea.ui;

import de.rabea.game.Replay;

public class InputFormatter {
    public Replay formatForReplayOption(String userInput) {
        String formatted = userInput.trim().toLowerCase();
        if (formatted.equals("y")) {
            return Replay.YES;
        } else if (formatted.equals("n")) {
            return Replay.NO;
        } else {
            return null;
        }
    }

    public boolean isInteger(String userEntry) {
        try {
            Integer.parseInt(userEntry);
            return true;
        } catch( NumberFormatException e ){
            return false;
        }
    }

    public Integer subtractOneToMatchArrayIndex(Integer position) {
        position --;
        return position;
    }
}
