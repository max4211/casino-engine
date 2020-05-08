package engine.dealer;

import Utility.HashNoise;
import Utility.StringPair;

/**
 * Encapsulates a suit and a value
 * In all card games, card objects exist and are held by a hand
 * @author Max Smith
 */
public class Card implements CardInterface {

    private final String mySuit;
    private final double myValue;
    private final int myID;

    private static final String SEPARATOR = " of ";

    /**
     * Construct a card from separate parameters
     * @param suit is the suit of the card
     * @param value double parsed value (quantity/power of card e.g. 10)
     */
    public Card(String suit, double value) {
        this.mySuit = suit;
        this.myValue = value;
        this.myID = HashNoise.addNoise(this);
    }

    /**
     * Construct a card form StringPair of objects
     * @param stringPair contains suit and value with getters
     */
    public Card(StringPair stringPair) {
        this.mySuit = stringPair.getKey();
        this.myValue = Double.parseDouble(stringPair.getValue());
        this.myID = HashNoise.addNoise(this);
    }

    /**
     * Called by evaluator package in classification of hands
     * @return the suit of the card
     */
    @Override
    public String getSuit() {
        return mySuit;
    }

    /**
     * Called by evaluator package in classification of hands
     * @return the numerical value of the card
     */
    @Override
    public double getValue() {
        return myValue;
    }

    /**
     * Get hashcode (ID) of card
     * @return s id of card
     */
    @Override
    public int getID() {
        return this.myID;
    }

    @Override
    public String toString() {
        return myValue + SEPARATOR + mySuit;
    }

}
