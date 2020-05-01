package controller.cardshow;

/**
 * Functional interface to show cards in the view
 * @author Max Smith
 */
@FunctionalInterface
public interface ShowCardInView {

    /**
     * Using tracking system within the view to accomplish without knowledge of the view
     * @param playerID player id hash
     * @param betID bet id (hash)
     * @param cardID card id (hash)
     */
    void showCard(int playerID, int betID, int cardID);

}
