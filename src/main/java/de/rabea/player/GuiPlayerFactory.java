package de.rabea.player;

import de.rabea.game.GameMode;
import de.rabea.game.Player;
import de.rabea.game.PlayerFactory;

import static de.rabea.game.Mark.O;
import static de.rabea.game.Mark.X;

public class GuiPlayerFactory implements PlayerFactory {

    @Override
    public Player createPlayer1(GameMode gameMode) {
        if (gameMode == GameMode.GuiHumanVsGuiHuman ||
                gameMode == GameMode.GuiHumanVsComputer) {
            return new GuiPlayer(X);
        } else {
            return new UnbeatableComputerPlayer(X);
        }
    }

    @Override
    public Player createPlayer2(GameMode gameMode) {
        if (gameMode == GameMode.GuiHumanVsComputer ||
                gameMode == GameMode.ComputerVsComputer ||
                gameMode == GameMode.GuiHumanVsComputer) {
            return new UnbeatableComputerPlayer(O);
        } else {
            return new GuiPlayer(O);
        }
    }
}
