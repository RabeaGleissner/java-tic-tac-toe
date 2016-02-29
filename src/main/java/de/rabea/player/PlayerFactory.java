package de.rabea.player;

import de.rabea.game.GameMode;
import de.rabea.game.Player;
import de.rabea.gui.GuiPlayer;
import de.rabea.ui.ConsoleUi;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;

public class PlayerFactory {
    private final ConsoleUi userInterface;

    public PlayerFactory(ConsoleUi userInterface) {
        this.userInterface = userInterface;
    }

    public Player createPlayer(GameMode gameMode) {
        if (gameMode == GameMode.HumanVsHuman ||
                gameMode == GameMode.HumanVsComputer) {
            return new HumanPlayer(userInterface, X);
        } else if (gameMode == GameMode.ComputerVsComputer ||
                gameMode == GameMode.ComputerVsHuman) {
            return new UnbeatableComputerPlayer(X);
        } else {
            return new GuiPlayer(X);
        }
    }

    public Player createOpponent(GameMode gameMode) {
        if (gameMode == GameMode.HumanVsComputer ||
                gameMode == GameMode.ComputerVsComputer ||
                gameMode == GameMode.GuiHumanVsComputer) {
            return new UnbeatableComputerPlayer(O);
        } else if (gameMode == GameMode.HumanVsHuman ||
                gameMode == GameMode.ComputerVsHuman) {
            return new HumanPlayer(userInterface, O);
        } else {
            return new GuiPlayer(O);
        }
    }
}
