package xml.xmlreader.interfaces;

import Utility.handbundle.HandBundle;

import java.util.List;

public interface HandReaderInterface {

    /**
     * Translates the XML tag for Winning Java hands into a List of String objects naming each Hand
     * The first entry is the best hand (trumps all) and the last entry is the worst hand (can still win, but loses to every entry before it)
     *
     * @return a List of HandBundles containing the names of all Hands that can win in a given game
     */
    List<HandBundle> getWinningHands();

    /**
     * Translates the XML tag for Losing Java jands into naming each Hand
     * Any Hand that is classified as an entry in this list is automatically removed from the game and Garbage Collected by the Table
     *
     * @return a List of HandBundles containing the names of all Hands that can lose in a given game
     */
    List<HandBundle> getLosingHands();

    /**
     * Parse CardsInHand tag from XML file
     * @return the appropriate cards in hand tag value
     */
    int getCardsInHand();
}
