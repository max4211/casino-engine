package GameView;

import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.List;

public class BetView implements ViewInterface {

    private VBox myView;
    private HandView myHand;
    private WagerView myWager;

    private int myID;

    // TODO: bind to handview
    private static final int CARD_WIDTH = 56;
    private static final int HEIGHT = 20;
    private int numberOfCards;

    private Formatter myFormatter;

    public BetView(List<CardTriplet> hand, double wager, int id) {
        myView = new VBox();
        myFormatter = new Formatter();
        numberOfCards = hand.size();
        myID = id;

        myFormatter.formatFixedVBox(myView, HEIGHT, CARD_WIDTH * numberOfCards);
        myHand = new HandView(hand);
        myWager = new WagerView(wager);

        myView.getChildren().addAll(myHand.getView(), myWager.getView());
    }

    public void updateWager(double amount) {
        if (amount > 0) myWager.updateWager(amount);
    }

    public void addCard(CardTriplet newCard) {
        numberOfCards++;
        myFormatter.updateVBoxWidth(myView, CARD_WIDTH * numberOfCards);
        myHand.addCardView(newCard);
    }

    // TODO: this checks index twice (again in handview): good or bad?
    public void showCard(int id) {
        myHand.showCard(id);
    }

    public VBox getView() {
        return myView;
    }
}
