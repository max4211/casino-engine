package UI.GameView;

import UI.Interfaces.TaggableInterface;
import UI.Interfaces.NodeViewInterface;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.VBox;

import java.util.List;

public class BetView implements NodeViewInterface, TaggableInterface {

    private VBox myView;
    private HandView myHand;
    private WagerView myWager;

    private int myID;

    // TODO: bind to handview
    private static final int CARD_WIDTH = 70;
    private static final int HEIGHT = 20;
    private static final int DEFAULT_WIDTH = 112;
    private static final int EMPTY = 0;
    private int numberOfCards;

    private Formatter myFormatter;

    public BetView(List<CardTriplet> hand, double wager, int id) {
        myView = new VBox();
        myFormatter = new Formatter();
        numberOfCards = hand.size();
        myID = id;

        int initialWidth;
        if (numberOfCards == EMPTY) initialWidth = DEFAULT_WIDTH;
        else initialWidth = CARD_WIDTH * numberOfCards;
        myFormatter.formatFixedVBox(myView, HEIGHT, initialWidth);

        myHand = new HandView(hand);
        myWager = new WagerView(wager);

        myView.getChildren().addAll(myHand.getView(), myWager.getView());
    }

    public void updateWager(double amount) {
        myWager.updateWager(amount);
    }

    public void addCard(CardTriplet newCard) {
        numberOfCards++;
        myFormatter.updateVBoxWidth(myView, CARD_WIDTH * numberOfCards);
        myHand.addCard(newCard);
    }

    public void addCardIfAbsent(CardTriplet checkedCard) {
        if (!myHand.hasCard(checkedCard.getID())) addCard(checkedCard);
    }

    public void removeCard(int cardID) {
        myHand.removeCard(cardID);
    }

    public void hideCard(int cardID) {
        myHand.hideCard(cardID);
    }

    // TODO: this checks index twice (again in handview): good or bad?
    public void showCard(int cardID) {
        myHand.showCard(cardID);
    }

    public VBox getView() {
        return myView;
    }

    public boolean hasSameID(int ID) {
        return myID == ID;
    }
}
