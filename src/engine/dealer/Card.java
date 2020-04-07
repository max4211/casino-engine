package engine.dealer;

import data.xmlreader.Pair;

public class Card implements CardInterface {

    private final String mySuit;
    private final double myValue;
    private boolean isCommunal = false;

    private static final String SEPARATOR = " of ";

    public Card(String suit, double value) {
        this.mySuit = suit;
        this.myValue = value;
    }

    public Card(Pair pair) {
        this.mySuit = pair.getKey();
        this.myValue = Double.parseDouble(pair.getValue());
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
    public String toString() {
        return myValue + SEPARATOR + mySuit;
    }

}
