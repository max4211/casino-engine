package controller.cardshow;

import UI.Utilities.CardTriplet;

/**
 * Functional interface used by CardShow policies
 * @author Max Smith
 */
@FunctionalInterface
public interface AddCardToView {

    /**
     * Mirros the addcardIfAbsent method in the game view, preserve MV separation
     * @param cardTriplet is the triplet of card values
     * @param playerID is the current active player hash
     * @param betID is the current active bet hash
     */
    void addCardIfAbsent(CardTriplet cardTriplet, int playerID, int betID);

}
