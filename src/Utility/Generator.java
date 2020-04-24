package Utility;

import UI.Utilities.CardTriplet;
import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.ArrayList;
import java.util.List;

public interface Generator {

    static CardTriplet createCardTriplet(Card c) {
        return new CardTriplet(c.getValue(), c.getSuit(), c.getID());
    }

    static List<CardTriplet> createTripletList(PlayerHand h) {
        return createTripletList(h.getCards());
    }

    static List<CardTriplet> createTripletList(List<Card> cardList) {
        List<CardTriplet> list = new ArrayList<>();
        for (Card c: cardList)
            list.add(Generator.createCardTriplet(c));
        return list;
    }
}
