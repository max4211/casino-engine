package UI.GameView;

import UI.Interfaces.TaggableInterface;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BetView implements NodeViewInterface, TaggableInterface {

    private VBox myView;
    private HandView myHand;
    private BetInfo myInfo;

    private int myID;

    // TODO: bind to handview
    private static final int CARD_WIDTH = 70;
    private static final int HEIGHT =40;
    private static final int DEFAULT_WIDTH = 112;
    private static final int EMPTY = 0;
    private int numberOfCards;

    private static final List<CardTriplet> EMPTY_HAND = new ArrayList<>();
    private static final String NO_CLASSIFICATION = "";

    private Formatter myFormatter;

    public BetView(List<CardTriplet> hand, double wager, String classification, int id, LanguageBundle languageBundle) {
        myView = new VBox();
        myFormatter = new Formatter();
        numberOfCards = hand.size();
        myID = id;

        int initialWidth;
        if (numberOfCards == EMPTY) initialWidth = DEFAULT_WIDTH;
        else initialWidth = CARD_WIDTH * numberOfCards;
        myFormatter.formatFixedVBox(myView, HEIGHT, initialWidth);

        myHand = new HandView(hand);
        myInfo = new BetInfo(wager, classification, languageBundle);

        myView.getChildren().addAll(myHand.getView(), myInfo.getView());
    }

    public BetView(double wager, int id, LanguageBundle languageBundle) {
        this(EMPTY_HAND, wager, NO_CLASSIFICATION, id, languageBundle);
    }

    public void updateWager(double newAmount) {
        myInfo.updateWager(newAmount);
    }

    public void updateClassification(String newClassification) {myInfo.updateClassification(newClassification);}

    public void addCard(CardTriplet newCard) {
        numberOfCards++;
        myFormatter.updateVBoxWidth(myView, CARD_WIDTH * numberOfCards);
        myHand.addCard(newCard);
    }

    public void addCardIfAbsent(CardTriplet checkedCard) {
        if (!myHand.hasCard(checkedCard.getID())) addCard(checkedCard);
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

    public void updateLanguage() {
        myInfo.updateLanguage();
    }

    public void setLoser() {
        myInfo.setLoser();
    }

    public void setWinner() {
        myInfo.setWinner();
    }
}
