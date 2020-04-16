package controller.interfaces;

import Utility.CardTriplet;
import engine.bet.Bet;

import java.util.function.Consumer;

public interface ControllerInterface {

    /**
     * Signal from the game constructor to enter the game loop
     * Called once all objects have successfully been created
     */
    void startGame();
}
