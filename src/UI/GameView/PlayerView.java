package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Interfaces.TaggableNode;
import UI.Utilities.CardTriplet;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class PlayerView implements StylizedNode, TaggableNode, LanguageResponder {

    private List<BetView> myBets;
    private PlayerInfo myInfo;
    private HBox myPlayerView;
    private int myID;

    public PlayerView(String name, int ID, double bankroll, LanguageBundle languageBundle) {
        myPlayerView = new HBox();
        Formatter.formatPlayerView(myPlayerView);
        StylizedNode.setStyleID(myPlayerView, this.getClass());
        myID = ID;
        myBets = new ArrayList<>();
        myInfo = new PlayerInfo(name, bankroll, languageBundle);
        myPlayerView.getChildren().add(myInfo.getView());
    }

    @Override
    public HBox getView() {
        return myPlayerView;
    }

    @Override
    public boolean hasSameID(int ID) {
        return myID == ID;
    }

    @Override
    public void updateLanguage() {
        myInfo.updateLanguage();
        for (BetView tempBetView : myBets) tempBetView.updateLanguage();
    }

    public void addBet(List<CardTriplet> hand, double wager, String classification, int betID, LanguageBundle languageBundle, String cardImage) {
        displayBetView(new BetView(hand, wager, classification, betID, languageBundle, cardImage));
    }

    public void addBet(double wager, int betID, LanguageBundle languageBundle) {
        displayBetView(new BetView(wager, betID, languageBundle));
    }

    private void displayBetView(BetView addedBetView) {
        myBets.add(addedBetView);
        myPlayerView.getChildren().add(addedBetView.getView());
    }

    public void removeBet(int betID) {
        BetView removedBet = getBet(betID);
        myBets.remove(removedBet);
        myPlayerView.getChildren().remove(removedBet.getView());
    }

    public void showCard(int betID, int cardID) {
        getBet(betID).showCard(cardID);
    }

    public void addCardIfAbsent(CardTriplet cardInfo, int betID, String cardImage) {
        getBet(betID).addCardIfAbsent(cardInfo, cardImage);
    }

    public void updateWager(int betID, double newWager) {getBet(betID).updateWager(newWager);}

    public void updateBankRoll(double newBankroll) {
        myInfo.updateBankroll(newBankroll);
    }

    public void clearBets() {
        for (BetView deletedBetView : myBets) myPlayerView.getChildren().remove(deletedBetView.getView());
        myBets.clear();
    }

    private BetView getBet(int ID) {
        for (BetView tempBetView : myBets) {
            if (tempBetView.hasSameID(ID)) return tempBetView;
        }
        return null;
    }

    public void hideCard(int betID, int cardID) {
        getBet(betID).hideCard(cardID);
    }

    public void updateClassification(int betID, String newClassification) {
        getBet(betID).updateClassification(newClassification);
    }

    public void setLoser(int betID) {
        getBet(betID).setLoser();
    }

    public void setWinner(int betID) {
        getBet(betID).setWinner();
    }

    public void hideAllClassifications() {
        for (BetView tempBetView : myBets) tempBetView.hideClassification();
    }

    public void showAllClassifications() {
        for (BetView tempBetView : myBets) tempBetView.showClassification();
    }
}
