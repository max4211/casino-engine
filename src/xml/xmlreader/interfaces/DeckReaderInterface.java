package xml.xmlreader.interfaces;

import Utility.StringPair;

import java.util.List;

public interface DeckReaderInterface {

    /**
     * Method that translates the XML Deck tag to a List of Pair objects holding the Suit (String) and Value (Integer) for each Card.
     * Called in CardDistribution module to assemble the deck.
     *
     * @return a List with each Pair entry containing the suit and value of a card in the deck.
     */
    List<StringPair> getDeck();

}
