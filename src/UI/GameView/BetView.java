package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.TaggableNode;
import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import UI.Utilities.CardTriplet;
import UI.Utilities.Formatter;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BetView implements StylizedNode, TaggableNode, LanguageResponder {

    private VBox myBetView;
    private HandView myHandView;
    private BetInfo myBetInfo;

    private int myID;
    private int numberOfCards;

    private static final List<CardTriplet> EMPTY_HAND = new ArrayList<>();
    private static final String NO_CLASSIFICATION = "";
    private static final String NO_CARD_IMAGE = "";

    public BetView(List<CardTriplet> hand, double wager, String classification, int id, LanguageBundle languageBundle, String cardImage) {
        myBetView = new VBox();
        numberOfCards = hand.size();
        Formatter.formatBetView(myBetView, numberOfCards);
        myID = id;

        myHandView = new HandView(hand, cardImage);
        myBetInfo = new BetInfo(wager, classification, languageBundle);

        myBetView.getChildren().addAll(myHandView.getView(), myBetInfo.getView());
    }

    public BetView(double wager, int id, LanguageBundle languageBundle) {
        this(EMPTY_HAND, wager, NO_CLASSIFICATION, id, languageBundle, NO_CARD_IMAGE);
    }

    @Override
    public VBox getView() {
        return myBetView;
    }

    @Override
    public boolean hasSameID(int ID) {
        return myID == ID;
    }

    public void updateWager(double newAmount) {
        myBetInfo.updateWager(newAmount);
    }

    public void updateClassification(String newClassification) {
        myBetInfo.updateClassification(newClassification);
    }

    public void addCard(CardTriplet newCard, String cardImage) {
        numberOfCards++;
        Formatter.updateBetViewWidth(myBetView, numberOfCards);
        myHandView.addCard(newCard, cardImage);
    }

    public void addCardIfAbsent(CardTriplet newCard, String cardImage) {
        if (!myHandView.hasCard(newCard.getID())) addCard(newCard, cardImage);
    }
    
    public void hideCard(int cardID) {
        myHandView.hideCard(cardID);
    }

    public void showCard(int cardID) {
        myHandView.showCard(cardID);
    }

    public void updateLanguage() {
        myBetInfo.updateLanguage();
    }

    public void setLoser() {
        myBetInfo.setLoser();
    }

    public void setWinner() {
        myBetInfo.setWinner();
    }
}
