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
    private PlayerView myMainPlayer;
    private AllPlayersView myOtherPlayers;
    private HandView myAdversary;

    public GameView() {
        myBorderPane = new BorderPane();
        myOtherPlayers = new AllPlayersView();
        myBorderPane.setLeft(myOtherPlayers.getView());
    }

    public BorderPane getView() {
        return myBorderPane;
    }

    @Override
    public void addCard(CardTriplet cardInfo, int playerID, int betID) {
        getPlayerView(playerID).addCard(cardInfo, betID);
    }

    @Override
    public void removeCard(int playerID, int betID, int cardID) {
        getPlayerView(betID).removeCard(betID, cardID);
    }

    @Override
    public void showCard(int playerID, int betID, int cardID) {
        getPlayerView(betID).showCard(betID, cardID);
    }

    @Override
    public void addBet(List<CardTriplet> handInfo, double wager, int playerID, int betID) {
        getPlayerView(playerID).addBet(handInfo, wager, betID);
    }

    @Override
    public void removeBet(int playerId, int betId) {
        getPlayerView(playerId).removeBet(betId);
    }

    @Override
    public String selectAction(List<String> actions) {
        return ActionSelector.selectAction(actions);
    }

    @Override
    public double selectWager(double minBet, double maxBet) {
        return WagerSelector.selectWager(minBet, maxBet);
    }

    @Override
    public void showAdversaryCard(int cardID) {
        myAdversary.showCard(cardID);
    }

    @Override
    public void addAdversaryCard(CardTriplet cardInfo) {
        myAdversary.addCard(cardInfo);
    }


    @Override
    public void renderAdversary(List<CardTriplet> hand) {
        myAdversary = new HandView(hand);
        myBorderPane.setTop(myAdversary.getView());
    }

    @Override
    public void renderTable(String file) {
        TableView table = new TableView(file);
        myBorderPane.setCenter(table.getView());
    }

    @Override
    public void addPlayer(String name, int playerId, double bankroll) {
        myOtherPlayers.addPlayer(name, playerId, bankroll);
    }

    @Override
    public void removePlayer(int playerId) {

    }

    @Override
    public void updateMainPlayer(int playerID) {
        if (!myOtherPlayers.hasPlayerView(playerID)) return;

        if (myMainPlayer != null) {
            myBorderPane.setBottom(myMainPlayer.getView());
            myOtherPlayers.addPlayer(myMainPlayer);
        }

        myMainPlayer = myOtherPlayers.getPlayerView(playerID);
        myOtherPlayers.removePlayer(playerID);
    }

    private PlayerView getPlayerView(int playerID) {
        if (myMainPlayer != null && myMainPlayer.hasSameID(playerID)) return myMainPlayer;
        return myOtherPlayers.getPlayerView(playerID);
    }
}
