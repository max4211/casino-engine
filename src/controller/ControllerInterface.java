package controller;

import engine.bet.Bet;

public interface ControllerInterface {

    /**
     * Lambda function passed into Table to prompt all players to place entry bets
     * @param bet is the bet placed by the player
     */
    void acceptBet(Bet bet);

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
