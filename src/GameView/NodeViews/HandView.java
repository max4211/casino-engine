package GameView.NodeViews;

import GameView.NodeViews.Interfaces.NodeViewInterface;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class HandView implements NodeViewInterface {

    private HBox myHand;
    private List<CardView> myCards;
    private Formatter myFormatter = new Formatter();


    public HandView(List<CardTriplet> allCards) {
        myHand = new HBox();
        myFormatter.formatUnfixedHBox(myHand);
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

    public void removeCard(int cardID) {
        for (CardView tempCardView : myCards) {
            if (tempCardView.hasSameID(cardID)) {
                myHand.getChildren().remove(tempCardView);
                return;
            }
        }
    }
 }
