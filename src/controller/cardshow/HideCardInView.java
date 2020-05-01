package controller.cardshow;

/**
 * Funcitonal interface to hide the card in the view, without knowledge of the view
 * @author Max Smith
 */
@FunctionalInterface
public interface HideCardInView {

    /**
     * Using tracking system within the view, hide the card (from the backend)
     * @param playerID player id hash
     * @param betID bet id (hash)
     * @param cardID card id (hash)
     */
    void hideCard(int playerID, int betID, int cardID);

}
