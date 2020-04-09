package engine.bet;

import engine.hand.Hand;

public interface BetInterface {

    /**
     * Called by the ActionFactory and Controller to get the Bet's hand
     * @return the hand inside of the bet
     */
    Hand getHand();

    /**
     * Called by the Table to get the wager on the bet
     * @return
     */
    double getWager();
}
