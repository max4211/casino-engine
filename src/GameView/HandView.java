package GameView;

import Formatting.Formatter;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class HandView implements ViewInterface {

    private HBox myHand;
    private List<CardView> myCards;
    private Formatter myFormatter = new Formatter();

    private static final int VALUE_INDEX = 0;
    private static final int SUIT_INDEX = 1;

    public HandView(List<Pair<Double, String>> allCards) {
        myHand = new HBox();
        myFormatter.formatUnfixedHBox(myHand);
        myCards = new ArrayList<>();

        for (Pair<Double, String> cardInfo : allCards) {
            CardView tempCardView = new CardView(cardInfo);
            myCards.add(tempCardView);
            myHand.getChildren().add(tempCardView.getView());
        }
    }

    // Best way to do this?
    public void addCardView(Pair<Double, String> newCard) {
        CardView addedCardView = new CardView(newCard);
        myHand.getChildren().add(addedCardView.getView());
    }

    // TODO: better way to do this?
    public void showCard(int index) {
        if (index < myCards.size() && index > 0) {
            myCards.get(index).showCard();
        }
    }

    //TODO: copy this? make this unmodifiable...
    public HBox getView() {
        return myHand;
    }
}
