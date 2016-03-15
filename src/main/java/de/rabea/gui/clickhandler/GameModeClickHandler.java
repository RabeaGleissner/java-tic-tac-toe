package de.rabea.gui.clickhandler;

import de.rabea.game.GameFactory;
import de.rabea.game.GameMode;
import de.rabea.gui.ClickHandler;
import de.rabea.gui.GameContext;

public class GameModeClickHandler implements ClickHandler {

    private GameFactory gameFactory;
    private GameContext context;

    public GameModeClickHandler(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public GameModeClickHandler(GameContext context) {
        this.context = context;
    }

    @Override
    public void action(String gameOption) {
        GameMode gameMode;
        if (gameOption.equals("Human vs Human")) {
            gameMode = GameMode.GuiHumanVsGuiHuman;
        } else {
            gameMode = GameMode.GuiHumanVsComputer;
        }
        context.setGame(gameFactory.createGame(gameMode));
    }
}
