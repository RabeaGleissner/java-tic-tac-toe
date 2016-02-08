package de.rabea.player;

import de.rabea.game.GameMode;
import de.rabea.game.Player;
import de.rabea.ui.UserInterface;

import static de.rabea.game.GameMode.*;
import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;

public class PlayerFactory {
    private UserInterface userInterface;

    public PlayerFactory(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public Player createPlayer(GameMode gameMode) {
        if (gameMode == HumanVsHuman || gameMode == HumanVsComputer) {
            return new HumanPlayer(userInterface, X);
        } else {
            return new UnbeatableComputerPlayer(X);
        }
    }

    public Player createOpponent(GameMode gameMode) {
        if (gameMode == HumanVsComputer || gameMode == ComputerVsComputer) {
            return new UnbeatableComputerPlayer(O);
        } else {
            return new HumanPlayer(userInterface, O);
        }
    }
}
