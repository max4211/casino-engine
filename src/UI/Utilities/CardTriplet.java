package UI.Utilities;

/**
 * Utility class used to provide the UI with information needed to create a card in a succinct manner.
 * Encodes the suit, value, and ID of a single card.
 * @author Eric Doppelt
 */
public class CardTriplet {

    private final Double myValue;
    private final String mySuit;
    private final int myID;

    /**
     * Basic constructor that initializes a card triplet with its relevant info.
     * @param value is the value of the card, or the digits of it.
     * @param suit is the suit of the card, or the String description of its type.
     * @param id is the tracking number of the card.
     */
    public CardTriplet(double value, String suit, int id) {
        myValue = value;
        mySuit = suit;
        myID = id;
    }

    /**
     * Basic getter method to get the card's value.
     * @return the value of the card
     */
    public Double getValue() {
        return myValue;
    }

    /**
     * Basic getter method to get the suit of the card.
     * @return the suit of the card
     */
    public String getSuit() { return mySuit; }

    /**
     * Basic getter method to get the ID of the card.
     * @return the tracking number of the card.
     */
    public int getID() { return myID; };
}
