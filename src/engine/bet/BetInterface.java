package engine.bet;

import engine.dealer.Card;
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

    /**
     * Accept a card from the dealer through the table
     * @param c card to accept to the bet
     */
    void acceptCard(Card c);
}
