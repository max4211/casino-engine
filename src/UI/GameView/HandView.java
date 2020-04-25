package UI.GameView;

import UI.Interfaces.StylizedNode;
import UI.Utilities.CardTriplet;
import UI.Utilities.Formatter;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class HandView implements StylizedNode {

    private HBox myHand;
    private List<CardView> myCards;

    public HandView(List<CardTriplet> allCards, String cardImage) {
        myHand = new HBox();
        StylizedNode.setStyleID(myHand, this.getClass());
        Formatter.formatHandView(myHand);
        myCards = new ArrayList<>();

        for (CardTriplet cardInfo : allCards) {
            CardView tempCardView = new CardView(cardInfo, cardImage);
            myCards.add(tempCardView);
            myHand.getChildren().add(tempCardView.getView());
        }
    }

    @Override
    public HBox getView() {
        return myHand;
    }

    public void showCard(int id) {
        for (CardView tempCard : myCards) {
            if (tempCard.hasSameID(id)) {
                tempCard.showCard();
            }
        }
    }

    public void addCard(CardTriplet newCard, String cardImage) {
        CardView addedCardView = new CardView(newCard, cardImage);
        myCards.add(addedCardView);
        myHand.getChildren().add(addedCardView.getView());
    }

    public boolean hasCard(int cardID) {
        return getCard(cardID) != null;
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
