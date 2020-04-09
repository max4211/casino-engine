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

    /**
     * get hashcode of bet
     * @return id of bet
     */
    int getID();

    /**
     * Determines if bet is active
     * @return
     */
    boolean isActive();

    /**
     * Tells the controller if the bet needs a card
     * @return
     */
    boolean needsCard();

    /**
     * Called by action to modify active state
     * @param state
     */
    void setActive(boolean state);

    /**
     * Called by action to modify need card state
     * @param state
     */
    void setNeedsCard(boolean state);

    /**
     * Set the wager to a new amount
     * @param wager new wgaer amount
     */
    void setWager(double wager);
}
