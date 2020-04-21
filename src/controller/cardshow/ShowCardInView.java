package controller.cardshow;

@FunctionalInterface
public interface ShowCardInView {

    void showCard(int playerID, int betID, int cardID);

}
