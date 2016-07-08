package de.rabea.player;

import de.rabea.console.ConsoleUi;
import de.rabea.game.GameMode;
import de.rabea.game.Player;
import de.rabea.game.PlayerFactory;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;

public class ConsolePlayerFactory implements PlayerFactory {
    private final ConsoleUi userInterface;

    public ConsolePlayerFactory(ConsoleUi userInterface) {
        this.userInterface = userInterface;
    }

    public Player createPlayer1(GameMode gameMode) {
        if (gameMode == GameMode.HumanVsHuman ||
                gameMode == GameMode.HumanVsComputer) {
            return new ConsolePlayer(userInterface, X);
        } else {
            return new UnbeatableComputerPlayer(X);
        }
    }

    public Player createPlayer2(GameMode gameMode) {
        if (gameMode == GameMode.HumanVsComputer ||
                gameMode == GameMode.ComputerVsComputer ||
                gameMode == GameMode.GuiHumanVsComputer) {
            return new UnbeatableComputerPlayer(O);
        } else {
            return new ConsolePlayer(userInterface, O);
        }
    }
}
