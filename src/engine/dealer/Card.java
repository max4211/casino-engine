package engine.dealer;

import xmlreader.Pair;

public class Card implements CardInterface {

    private final String mySuit;
    private final double myValue;
    private final int myID;
    private boolean isCommunal = false;

    private static final String SEPARATOR = " of ";

    public Card(String suit, double value) {
        this.mySuit = suit;
        this.myValue = value;
        this.myID = this.hashCode();
    }

    public Card(Pair pair) {
        this.mySuit = pair.getKey();
        this.myValue = Double.parseDouble(pair.getValue());
        this.myID = this.hashCode();
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
