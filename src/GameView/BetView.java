package GameView;

import Formatting.Formatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.List;

public class BetView implements ViewInterface {

    private VBox myView;
    private HandView myHand;
    private WagerView myWager;

    // TODO: bind to handview
    private static final int CARD_WIDTH = 56;
    private static final int HEIGHT = 20;
    private int numberOfCards;

    private Formatter myFormatter;

    public BetView(List<Pair<Double, String>> hand, double wager) {
        myView = new VBox();
        myFormatter = new Formatter();
        numberOfCards = hand.size();
        myFormatter.formatFixedVBox(myView, HEIGHT, CARD_WIDTH * numberOfCards);
        myHand = new HandView(hand);
        myWager = new WagerView(wager);

        myView.getChildren().addAll(myHand.getView(), myWager.getView());
    }

    public void updateWager(double amount) {
        myWager.updateWager(amount);
    }

    public void addCard(Pair<Double, String> newCard) {
        numberOfCards++;
        myFormatter.updateVBoxWidth(myView, CARD_WIDTH * numberOfCards);
        myHand.addCardView(newCard);
    }

    // TODO: this checks index twice (again in handview): good or bad?
    public void showCard(int index) {
        if (index < numberOfCards && index > 0) myHand.showCard(index);
    }

    public VBox getView() {
        return myView;
    }
}
