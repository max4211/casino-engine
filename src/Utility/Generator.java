package Utility;

import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.ArrayList;
import java.util.List;

public interface Generator {

    static CardTriplet createCardTriplet(Card c) {
        return new CardTriplet(c.getValue(), c.getSuit(), c.getID());
    }

    static List<CardTriplet> createTripletList(PlayerHand h) {
        List<CardTriplet> list = new ArrayList<>();
        for (Card c: h.getCards())
            list.add(Generator.createCardTriplet(c));
        return list;
    }
}
