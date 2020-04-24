package controller.cardshow;

import UI.Utilities.CardTriplet;

@FunctionalInterface
public interface AddCardToView {

    void addCardIfAbsent(CardTriplet cardTriplet, int playerID, int betID);

}
