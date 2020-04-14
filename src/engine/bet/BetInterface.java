package engine.bet;

import engine.dealer.Card;
import engine.hand.PlayerHand;

public interface BetInterface {

    /**
     * Called by the ActionFactory and Controller to get the Bet's hand
     * @return the hand inside of the bet
     */
    PlayerHand getHand();

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
     * Called by action to modify active state
     * @param state
     */
    void setActive(boolean state);

    /**
     * Set the wager to a new amount
     * @param wager new wgaer amount
     */
    void setWager(double wager);
}
