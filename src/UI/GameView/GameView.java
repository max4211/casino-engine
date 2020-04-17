package UI.GameView;

import UI.ExceptionHandling.ExceptionDisplayer;
import UI.GameView.Settings.LanguagePicker;
import UI.GameView.Settings.StylePicker;
import UI.Interfaces.Executor;
import UI.Interfaces.GameViewInterface;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.Selectors.ActionSelector;
import UI.Selectors.SelectorType;
import UI.Selectors.WagerSelector;
import Utility.CardTriplet;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class GameView implements GameViewInterface, NodeViewInterface {

    private BorderPane myBorderPane;
    private MainPlayerView myMainPlayer;
    private OtherPlayersView myOtherPlayers;
    private HandView myAdversary;
    private HandView myCommons;
    private ExceptionDisplayer myExceptionDisplayer;
    private LanguageBundle myLanguageBundle;
    private static final int DEFAULT_LANGUAGE_INDEX = 0;

    public GameView(List<String> styleSheets, List<String> languages) {
        myBorderPane = new BorderPane();
        myOtherPlayers = new OtherPlayersView();
        myBorderPane.setLeft(myOtherPlayers.getView());
        myMainPlayer = new MainPlayerView();
        myBorderPane.setBottom(myMainPlayer.getView());
        myLanguageBundle = new LanguageBundle(languages.get(DEFAULT_LANGUAGE_INDEX));
        myExceptionDisplayer = new ExceptionDisplayer("exceptionIcon.png", "fire.css", myLanguageBundle);

        VBox tempHolder = new VBox();
        tempHolder.getChildren().add(new StylePicker(styleSheets, e -> updateStyleSheet(e)).getView());
        tempHolder.getChildren().add(new LanguagePicker(languages, e -> updateLanguage(e)).getView());
        myBorderPane.setRight(tempHolder);
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
    public void renderCommonCards(List<CardTriplet> hand) {
        myCommons = new HandView(hand);
        myBorderPane.setCenter(myCommons.getView());
    }

    @Override
    public void addCommonCard(CardTriplet cardInfo) {
        myCommons.addCard(cardInfo);
    }

    @Override
    public void showCommonCard(int cardID) {
        myCommons.showCard(cardID);
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
        getPlayerView(playerID).addBet(handInfo, wager, betID, myLanguageBundle);
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
    public void addPlayer(String name, int playerId, double bankroll) {
        myOtherPlayers.addPlayer(name, playerId, bankroll, myLanguageBundle);
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
        myMainPlayer.waitUntilReady(SelectorType.WAGER);
        return WagerSelector.selectWager(minBet, maxBet);
    }

    @Override
    public String selectAction(List<String> actions) {
        myMainPlayer.waitUntilReady(SelectorType.ACTION);
        return ActionSelector.selectAction(actions);
    }

    @Override
    public void displayException(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ex.getLocalizedMessage());
        alert.showAndWait();
    }

    @Override
    public void promptNewGame(Executor startNewGame) {
        myMainPlayer.waitUntilReady(SelectorType.NEWGAME);
        startNewGame.run();
    }

    private PlayerView getPlayerView(int playerID) {
        if (myMainPlayer.holdsAPlayer() && myMainPlayer.hasSameID(playerID)) return myMainPlayer.getMainPlayer();
        return myOtherPlayers.getPlayerView(playerID);
    }

    private void updateStyleSheet(String newStylesheet) {
        myBorderPane.getStylesheets().clear();
        myBorderPane.getStylesheets().add(newStylesheet);
    }

    private void updateLanguage(String newLanguage) {
        myLanguageBundle.setLanguage(newLanguage);
        myMainPlayer.updateLanguage();
        myOtherPlayers.updateLanguage();
    }
}
