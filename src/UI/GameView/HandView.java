package UI.GameView;

import UI.Interfaces.StylizedNode;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class HandView implements StylizedNode {

    private HBox myHand;
    private List<CardView> myCards;
    private Formatter myFormatter = new Formatter();


    public HandView(List<CardTriplet> allCards) {
        myHand = new HBox();
        myFormatter.formatUnfixedCenter(myHand);
        myCards = new ArrayList<>();

        for (CardTriplet cardInfo : allCards) {
            CardView tempCardView = new CardView(cardInfo);
            myCards.add(tempCardView);
            myHand.getChildren().add(tempCardView.getView());
        }
    }


    //TODO: copy this? make this unmodifiable...
    public HBox getView() {
        return myHand;
    }

    /**
     * shows first card of its kind
     */
    public void showCard(int id) {
        for (CardView tempCard : myCards) {
            if (tempCard.hasSameID(id)) {
                tempCard.showCard();
                return;
            }
        }
    }

    /**
     *
     * @param newCard
     */
    public void addCard(CardTriplet newCard) {
        CardView addedCardView = new CardView(newCard);
        myCards.add(addedCardView);
        myHand.getChildren().add(addedCardView.getView());
    }

    public boolean hasCard(int cardID) {
        return getCard(cardID) != null;
    }


    public void removeCard(int cardID) {
        myCards.remove(cardID);
    }

    public void hideCard(int cardID) {
        getCard(cardID).hideCard();
    }

    public void clearHand() {
        myCards.clear();
        myHand.getChildren().clear();
    }

    private CardView getCard(int id) {
        for (CardView tempCardView : myCards) {
            if (tempCardView.hasSameID(id)) return tempCardView;
        }
        return null;
    }
 }
