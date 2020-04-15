package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import UI.Interfaces.TaggableInterface;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class PlayerView implements NodeViewInterface, TaggableInterface {

    private List<BetView> myBets;
    private PlayerInfoView myInfo;

    private HBox myView;
    private Formatter myFormatter;

    private int myID;

    public PlayerView(String name, int ID, double bankroll) {
        myView = new HBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedLeft(myView);
        myID = ID;
        myBets = new ArrayList<>();
        myInfo = new PlayerInfoView(name, bankroll);
        myView.getChildren().add(myInfo.getView());
    }

    public void addBet(List<CardTriplet> hand, double wager, int betID) {
        BetView addedBetView = new BetView(hand, wager, betID);
        myBets.add(addedBetView);
        myView.getChildren().add(addedBetView.getView());
    }

    public void removeBet(int betID) {
        BetView removedBet = getBet(betID);
        myBets.remove(removedBet);
        myView.getChildren().remove(removedBet.getView());
    }

    public void showCard(int betID, int cardID) {
        getBet(betID).showCard(cardID);
    }

    public HBox getView() {
        return myView;
    }

    public boolean hasSameID(int ID) {
        return myID == ID;
    }

    public void addCard(CardTriplet cardInfo, int betID) {
        getBet(betID).addCard(cardInfo);
    }

    public void addCardIfAbsent(CardTriplet cardInfo, int betID) {
        getBet(betID).addCardIfAbsent(cardInfo);
    }

    public void removeCard(int betID, int cardID) {
        for (BetView tempBetView : myBets) {
            if (tempBetView.hasSameID(betID)) {
                tempBetView.removeCard(cardID);
            }
        }
    }

    public void updateWager(int betID, double newWager) {getBet(betID).updateWager(newWager);}

    public void updateBankRoll(double newBankroll) {
        myInfo.updateBankroll(newBankroll);
    }

    public void clearBets() {
        for (BetView deletedBetView : myBets) myView.getChildren().remove(deletedBetView.getView());
        myBets.clear();
    }

    private BetView getBet(int ID) {
        for (BetView tempBetView : myBets) {
            if (tempBetView.hasSameID(ID)) return tempBetView;
        }
        return null;
    }

    public void hideCard(int betID, int cardID) {

    }
}
