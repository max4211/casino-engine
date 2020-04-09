package controller;

import engine.bet.Bet;

public interface ControllerInterface {

    /**
     * Lambda function passed into Table to prompt all players to place entry bets
     * @param bet is the bet placed by the player
     */
    void acceptBet(Bet bet);
}
