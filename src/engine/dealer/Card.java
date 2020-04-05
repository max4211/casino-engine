package engine.dealer;

public class Card implements CardInterface {

    private final String mySuit;
    private final double myValue;

    private static final String SEPARATOR = " of ";

    public Card(String suit, double value) {
        this.mySuit = suit;
        this.myValue = value;
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
    public String toString() {
        return myValue + SEPARATOR + mySuit;
    }

}
