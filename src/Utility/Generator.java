package Utility;

import UI.Utilities.CardTriplet;
import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate card triplets from various sources
 * Utility to assist controller creating view friendly packaged objects
 */
public interface Generator {

    /**
     * Create a card triplet from a single card
     * @param c input card
     * @return card triplet representation of card
     */
    static CardTriplet createCardTriplet(Card c) {
        return new CardTriplet(c.getValue(), c.getSuit(), c.getID());
    }

    /**
     * Generate list of triplets from a list of cards
     * @param h player hand (really just need cards)
     * @return list of card triplets
     */
    static List<CardTriplet> createTripletList(PlayerHand h) {
        return createTripletList(h.getCards());
    }

    /**
     * Generate list of triplets from a list of cards
     * @param cardList list of cards to make a triplet list of
     * @return list of card triplets
     */
    static List<CardTriplet> createTripletList(List<Card> cardList) {
        List<CardTriplet> list = new ArrayList<>();
        for (Card c: cardList)
            list.add(Generator.createCardTriplet(c));
        return list;
    }
}
