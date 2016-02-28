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
        switch (gameMode) {
            case HumanVsHuman:
            case HumanVsComputer:
                return new HumanPlayer(userInterface, X);
            case ComputerVsComputer:
            case ComputerVsHuman:
                return new UnbeatableComputerPlayer(X);
            case GuiHumanVsGuiHuman:
            case GuiHumanVsComputer:
                return new GuiPlayer(X);
            default:
                throw new RuntimeException("Illegal GameMode!");
        }
    }

    public Player createOpponent(GameMode gameMode) {
        switch (gameMode) {
            case HumanVsComputer:
            case ComputerVsComputer:
            case GuiHumanVsComputer:
                return new UnbeatableComputerPlayer(O);
            case HumanVsHuman:
            case ComputerVsHuman:
                return new HumanPlayer(userInterface, O);
            case GuiHumanVsGuiHuman:
                return new GuiPlayer(O);
            default:
                throw new RuntimeException("Illegal GameMode!");
        }
    }
}
