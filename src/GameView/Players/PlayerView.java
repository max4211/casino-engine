package GameView.Players;

import GameView.TaggableInterface;
import GameView.ViewInterface;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class PlayerView implements ViewInterface, TaggableInterface {

    private List<BetView> myBets;
    private PlayerDetailView myInfo;

    private HBox myView;
    private Formatter myFormatter;

    private int myID;

    public PlayerView(String name, double bankRoll, int ID) {
        myView = new HBox();
        myFormatter = new Formatter();
        myFormatter.formatUnfixedHBox(myView);

        myID = ID;
        myBets = new ArrayList<BetView>();
        myInfo = new PlayerDetailView(name, bankRoll);
        myView.getChildren().add(myInfo.getView());
    }

    public void addBet(List<CardTriplet> hand, double wager, int id) {
        BetView addedBetView = new BetView(hand, wager, id);
        myBets.add(addedBetView);
        myView.getChildren().add(addedBetView.getView());
    }

    public void removeBet(int id) {
        BetView removedBet = getBet(id);
        myBets.remove(removedBet);
        myView.getChildren().remove(removedBet.getView());
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

    private BetView getBet(int ID) {
        for (BetView tempBetView : myBets) {
            if (tempBetView.hasSameID(ID)) return tempBetView;
        }
        return null;
    }
}
