package de.rabea;

import de.rabea.game.Board;
import de.rabea.game.Game;
import de.rabea.game.Rules;
import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;

public class App
{
    public static void main( String[] args )
    {
        Board board = new Board();
        Game game = new Game(new UserInterface(new RealConsole()), board, new Rules(board));
        game.play();

    }
}
