package de.rabea.game;

import de.rabea.ui.RealConsole;
import de.rabea.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;

public class GameSetUpTest {

    GameSetUp gameSetUp;

    @Before
    public void setup() {
        gameSetUp = new GameSetUp(new UserInterface(new RealConsole(System.in, System.out)));
    }

    @Test
    public void itCreatesAComputerPlayerAsOpponentIfUserSelectsIt() {

    }

}