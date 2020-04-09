package controller;

import Utility.CardTriplet;
import engine.bet.Bet;

import java.util.function.Consumer;

public interface ControllerInterface {

    /**
     * lambda function passed through to table/view to accept action selected
     * @param action is the action selected by the user
     */
    void acceptAction(String action);

    /**
     * Signal from the game constructor to enter the game loop
     * Called once all objects have successfully been created
     */
    void startGame();
}
