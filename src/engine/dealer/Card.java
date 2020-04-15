package engine.dealer;

import Utility.HashNoise;
import Utility.StringPair;

public class Card implements CardInterface {

    private final String mySuit;
    private final double myValue;
    private final int myID;
    private boolean isCommunal = false;

    private static final String SEPARATOR = " of ";

    public Card(String suit, double value) {
        this.mySuit = suit;
        this.myValue = value;
        this.myID = HashNoise.addNoise(this);
    }

    public Card(StringPair stringPair) {
        this.mySuit = stringPair.getKey();
        this.myValue = Double.parseDouble(stringPair.getValue());
        this.myID = HashNoise.addNoise(this);
    }

    @Override
    public String getSuit() {
        return mySuit;
    }

    @Override
    public double getValue() {
        return myValue;
    }

    @Override
    public void assignCommunal() {
        this.isCommunal = true;
    }

    @Override
    public int getID() {
        return this.myID;
    }

    @Override
    public String toString() {
        return myValue + SEPARATOR + mySuit;
    }

}
