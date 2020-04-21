package controller.cardshow;

import Utility.CardTriplet;

@FunctionalInterface
public interface AddCardToView {

    void addCardIfAbsent(CardTriplet cardTriplet, int playerID, int betID);

}
