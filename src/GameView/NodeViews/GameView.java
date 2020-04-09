package GameView.NodeViews;

import GameView.NodeViews.Interfaces.GameViewInterface;
import GameView.Selectors.ActionSelector;
import GameView.Selectors.WagerSelector;
import Utility.CardTriplet;
import Utility.Formatter;
import javafx.scene.layout.BorderPane;
import GameView.NodeViews.Interfaces.NodeViewInterface;


import java.util.ArrayList;
import java.util.List;

public class GameView implements GameViewInterface, NodeViewInterface {

    private BorderPane myBorderPane;
    private List<PlayerView> allPlayers;
    private Formatter myFormatter;

    public GameView() {
        myBorderPane = new BorderPane();
        allPlayers = new ArrayList<>();
    }

    public BorderPane getView() {
        return myBorderPane;
    }

    @Override
    public void addCard(CardTriplet cardInfo, int playerID, int betID) {
        // getPlayerView(playerID).addCard(cardInfo, betID);
    }

    @Override
    public void removeCard(int playerID, int betID) {
        
    }

    @Override
    public void showCard(int betId, int cardId) {

    }

    @Override
    public void addBet(List<CardTriplet> handInfo, double wager, int betID, int playerID) {
        // getPlayerView(playerID).addBet(handInfo, wager, betID);
    }

    @Override
    public void removeBet(int playerId, int betId) {
        // getPlayerView(playerId).removeBet(betId);
    }

    @Override
    public String getAction(List<String> actions) {
        return ActionSelector.selectAction(actions);
    }

    @Override
    public double getWager(double minBet, double maxBet) {
        return WagerSelector.selectWager(minBet, maxBet);
    }

    @Override
    public void showAdversaryCard(int cardID) {

    }

    @Override
    public void addAdversaryCard(int cardID) {

    }


    @Override
    public void renderAdversary(List<CardTriplet> actions) {

    }

    @Override
    public void renderTable(String file) {

    }

    @Override
    public void addPlayer(int playerId, double bankroll) {

    }

    @Override
    public void removePlayer(int playerId) {

    }

    @Override
    public void updateMainPlayer(int playerId) {

    }

    private PlayerView getPlayerView(int ID) {
        for (PlayerView tempPlayerView : allPlayers) {
            if (tempPlayerView.hasSameID(ID)) return tempPlayerView;
        }
        // TODO: fix this error
        return null;
    }
}
