package engine.player;

import engine.bet.Bet;

import java.util.List;

public interface PlayerInterface {

    /**
     * get name of current player
     * @return
     */
    String getName();

    /**
     * get bankroll of current player
     * @return
     */
    double getBankroll();

    /**
     * get ID of current player
     * @return
     */
    int getID();

    /**
     * return a list of the players current bets for card distribution
     * @return
     */
    List<Bet> getBets();

    /**
     * Place bet (of size wager)
     * @param wager size of bet
     */
    int placeBet(double wager);
}
