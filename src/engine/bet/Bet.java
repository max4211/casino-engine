package engine.bet;

import Utility.HashNoise;
import engine.dealer.Card;
import engine.hand.PlayerHand;

/**
 * Encapsulates a wager and a player hand, as well as tags representing the
 * bets status within the round
 * Linked to a specific player (Players have a bet)
 * @author Max Smith
 */
public class Bet implements BetInterface {

    private int myID;
    private PlayerHand myPlayerHand;
    private double myWager;
    private boolean myRoundActive;
    private boolean myGameActive;
    private double myProfit = 0;
    private static final double ONE = 1.0;

    /**
     * Construct a bet from a specific wager
     * @param wager is the bet amount on the ahnd
     */
    public Bet(double wager) {
        this.myPlayerHand = new PlayerHand();
        this.myWager = wager;
        this.myID = HashNoise.addNoise(this);
        this.myRoundActive = true;
        this.myGameActive = true;
    }

    /**
     * Construct a bet from a wager and a set of cards
     * @param wager the double wagered on the hand
     * @param playerHand encapsulation of cards (playerhand)
     */
    public Bet(double wager, PlayerHand playerHand) {
        this.myPlayerHand = playerHand;
        this.myWager = wager;
        this.myID = HashNoise.addNoise(this);
        this.myRoundActive = true;
        this.myGameActive = true;
    }

    /**
     * Called by the ActionFactory and Controller to get the Bet's hand
     * @return the hand inside of the bet
     */
    @Override
    public PlayerHand getHand() {
        return this.myPlayerHand;
    }

    /**
     * Called by the Table to get the wager on the bet
     * @return
     */
    @Override
    public double getWager() {
        return this.myWager;
    }

    /**
     * Accept a card from the dealer through the table
     * @param c card to accept to the bet
     */
    @Override
    public void acceptCard(Card c) {
        this.myPlayerHand.acceptCard(c);
    }

    /**
     * get hashcode of bet
     * @return id of bet
     */
    @Override
    public int getID() {
        return this.myID;
    }

    /**
     * Determines if bet is active
     * @return current game active state
     */
    @Override
    public boolean isGameActive() {
        return this.myGameActive;
    }

    /**
     * Called by action to modify active state
     * @param state desired state
     */
    @Override
    public void setGameActive(boolean state) {
        this.myGameActive = state;
    }

    /**
     * Determines if bet is active
     * @return current round status
     */
    @Override
    public boolean isRoundActive() {
        return this.myRoundActive;
    }

    /**
     * Called by action to modify active state
     * @param state desired boolean status of round
     */
    @Override
    public void setRoundActive(boolean state) {
        this.myRoundActive = state;
    }

    /**
     * Set the wager to a new amount
     * @param wager new wgaer amount
     */
    @Override
    public void setWager(double wager) {
        this.myWager = wager;
    }

    /**
     * Called by player to get payoff of their bets
     * @return double value indicating payoff (multiplier?)
     */
    @Override
    public double getPayoff() {
        return this.myWager * (ONE + this.myPlayerHand.getClassification().getMultiplier()) + this.myProfit;
    }

    /**
     * Called by pot/controller to set the profit of a bet
     */
    @Override
    public void setProfit(double profit) {
        this.myProfit = profit;
    }


}
