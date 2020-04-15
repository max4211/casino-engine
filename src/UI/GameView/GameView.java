package UI.GameView;

import UI.Interfaces.GameViewInterface;
import UI.Selectors.ActionSelector;
import UI.Selectors.WagerSelector;
import Utility.CardTriplet;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import UI.Interfaces.NodeViewInterface;

import java.util.List;

public class GameView implements GameViewInterface, NodeViewInterface {

    private BorderPane myBorderPane;
    private MainPlayerView myMainPlayer;
    private OtherPlayersView myOtherPlayers;
    private HandView myAdversary;

    private static final String PATH_TO_STYLESHEETS = "././UI/StyleSheets/";
    private static final String DEFAULT_CSS = PATH_TO_STYLESHEETS + "Normal.css";
    private static final String DARK_MODE_CSS = PATH_TO_STYLESHEETS + "DarkMode.css";
    private String myStyleSheet;


    public GameView() {
        myBorderPane = new BorderPane();
        myOtherPlayers = new OtherPlayersView();
        myBorderPane.setLeft(myOtherPlayers.getView());

        myMainPlayer = new MainPlayerView();
        myBorderPane.setBottom(myMainPlayer.getView());

        myBorderPane.getStylesheets().add(DEFAULT_CSS);
        Button tempDarkMode = new Button();
        tempDarkMode.setOnAction(e -> updateStyleSheet(DARK_MODE_CSS));
        tempDarkMode.setText("DARKMODE!");
        myBorderPane.setRight(tempDarkMode);
        myStyleSheet = DEFAULT_CSS;
    }

    public BorderPane getView() {
        return myBorderPane;
    }

    @Override
    public void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID) {
        getPlayerView(playerID).addCardIfAbsent(cardInfo, betID);
    }

    @Override
    public void addCard(CardTriplet cardInfo, int playerID, int betID) {
        getPlayerView(playerID).addCard(cardInfo, betID);
    }

    @Override
    public void addAllCards(List<CardTriplet> allCards, int playerID, int betID) {
        for (CardTriplet cardInfo : allCards) getPlayerView(playerID).addCard(cardInfo, betID);
    }

    @Override
    public void addAllCardsIfAbsent(List<CardTriplet> allCards, int playerID, int betID) {
        for (CardTriplet cardInfo : allCards) getPlayerView(playerID).addCardIfAbsent(cardInfo, betID);
    }

    @Override
    public void removeCard(int playerID, int betID, int cardID) {
        getPlayerView(playerID).removeCard(betID, cardID);
    }

    @Override
    public void showCard(int playerID, int betID, int cardID) {
        getPlayerView(playerID).showCard(betID, cardID);
    }

    @Override
    public void hideCard(int playerID, int betID, int cardID) {
        getPlayerView(playerID).hideCard(betID, cardID);
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
    public void clearAllBets() {
        if (myMainPlayer.holdsAPlayer()) myMainPlayer.getMainPlayer().clearBets();
        myOtherPlayers.clearBets();
    }

    @Override
    public void clearAdversary() {
        if (myAdversary != null) myAdversary.clearHand();
    }

    @Override
    public void setWager(double newWager, int playerID, int BetID) {
        getPlayerView(playerID).updateWager(BetID, newWager);
    }

    @Override
    public void setBankRoll(double newBankroll, int playerID) {
        getPlayerView(playerID).updateBankRoll(newBankroll);
    }

    @Override
    public void showAdversaryCard(int cardID) {
        System.out.println("(GAMEVIEW): showing adversary card in view");
        myAdversary.showCard(cardID);
    }

    @Override
    public void addAdversaryCard(CardTriplet cardInfo) {
        System.out.printf("(GAMEVIEW): added adversary card (%s of %s) to view\n", cardInfo.getValue(), cardInfo.getSuit());
        myAdversary.addCard(cardInfo);
    }

    @Override
    public void renderAdversary(List<CardTriplet> hand) {
        System.out.println("RENDERING");
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
        if (myMainPlayer.holdsAPlayer() && myMainPlayer.hasSameID(playerId)) myMainPlayer.clear();
        else if (myOtherPlayers.hasPlayerView(playerId)) myOtherPlayers.removePlayer(playerId);
    }

    // TODO: fix this to avoid updating BorderPane all the time
    @Override
    public void setMainPlayer(int playerID) {
        if (!myOtherPlayers.hasPlayerView(playerID)) return;
        if (myMainPlayer.holdsAPlayer()) myOtherPlayers.addPlayer(myMainPlayer.getMainPlayer());
        myMainPlayer.setMainPlayer(myOtherPlayers.getPlayerView(playerID));
        myOtherPlayers.removePlayer(playerID);
    }

    @Override
    public double selectWager(double minBet, double maxBet) {
        return WagerSelector.selectWager(minBet, maxBet);
    }

    @Override
    public String selectAction(List<String> actions) {
        return ActionSelector.selectAction(actions);
    }

    @Override
    public void displayError(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ex.getLocalizedMessage());
        alert.showAndWait();
    }

    private PlayerView getPlayerView(int playerID) {
        if (myMainPlayer.holdsAPlayer() && myMainPlayer.hasSameID(playerID)) return myMainPlayer.getMainPlayer();
        return myOtherPlayers.getPlayerView(playerID);
    }

    private void updateStyleSheet(String newStylesheet) {
        myBorderPane.getStylesheets().remove(myStyleSheet);
        myBorderPane.getStylesheets().add(newStylesheet);
        myStyleSheet = newStylesheet;
    }
}
