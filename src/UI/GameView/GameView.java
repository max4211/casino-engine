package UI.GameView;

import UI.ExceptionHandling.ExceptionDisplayer;
import UI.Interfaces.GameCaller;
import UI.Interfaces.GameViewInterface;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import UI.Selectors.ActionSelector;
import UI.Selectors.SelectorType;
import UI.Selectors.WagerSelector;
import UI.Settings.SettingsBar;
import Utility.CardTriplet;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class GameView implements GameViewInterface, NodeViewInterface {

    private static final String NO_ACTION_INPUT = "";
    private static final double NO_WAGER_INPUT = -1;

    private VBox myVBox;
    private BorderPane myBorderPane;
    private MainPlayerView myMainPlayer;
    private OtherPlayersView myOtherPlayers;
    private HandView myAdversary;
    private HandView myCommons;
    private PotView myPotView;

    private SettingsBar mySettingsBar;
    private LanguageBundle myLanguageBundle;
    private ExceptionDisplayer myExceptionDisplayer;
    //FIXME: create a general constructor!
    private ActionSelector myActionSelector;
    private WagerSelector myWagerSelector;

    private static final int DEFAULT_LANGUAGE_INDEX = 0;
    private static final String GAME_CSS_ID = "game-box";

    public GameView(List<String> styleSheets, List<String> languages) {
        myVBox = new VBox();
        myVBox.setId(GAME_CSS_ID);
        myLanguageBundle = new LanguageBundle(languages.get(DEFAULT_LANGUAGE_INDEX));

        myBorderPane = new BorderPane();
        myOtherPlayers = new OtherPlayersView();
        myBorderPane.setLeft(myOtherPlayers.getView());
        myMainPlayer = new MainPlayerView(myLanguageBundle);
        myBorderPane.setBottom(myMainPlayer.getView());
        myExceptionDisplayer = new ExceptionDisplayer("exceptionIcon.png", "fire.css", myLanguageBundle);
        myWagerSelector = new WagerSelector(myLanguageBundle);
        myActionSelector = new ActionSelector(myLanguageBundle);
        mySettingsBar = new SettingsBar(e -> updateStyleSheet(e), styleSheets, e -> updateLanguage(e), languages, "construction.png", "information.png");

        myVBox.getChildren().addAll(mySettingsBar.getView(), myBorderPane);
        myVBox.setVgrow(myBorderPane, Priority.ALWAYS);
    }

    public VBox getView() {
        return myVBox;
    }

    @Override
    public void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID) {
        getPlayerView(playerID).addCardIfAbsent(cardInfo, betID);
    }

    @Override
    public void renderCommonCards(List<CardTriplet> hand) {
        myCommons = new HandView(hand);
        myBorderPane.setCenter(myCommons.getView());
    }

    @Override
    public void showCommonCard(int cardID) {
        myCommons.showCard(cardID);
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
        double chosenWager = myWagerSelector.selectWager(minBet, maxBet, e -> displayException(e));
        if (chosenWager != NO_WAGER_INPUT) return chosenWager;
        return selectWager(minBet, maxBet);
    }

    @Override
    public String selectAction(List<String> actions) {
        myMainPlayer.waitUntilReady(SelectorType.ACTION);
        String actionChosen = myActionSelector.selectAction(actions, e -> displayException(e));
        if (!actionChosen.equals(NO_ACTION_INPUT)) return actionChosen;
        return selectAction(actions);
    }

    @Override
    public void displayException(Exception ex) {
        myExceptionDisplayer.displayException(ex);
    }

    @Override
    public void promptNewGame(GameCaller startNewGame) {
        myMainPlayer.waitUntilReady(SelectorType.NEWGAME);
        startNewGame.startNewGame();
    }

    @Override
    public void displayText(String s) {
        Alert display = new Alert(Alert.AlertType.INFORMATION);
        display.setContentText(s);
        display.showAndWait();
    }

    @Override
    public void setLoser(int betID) {

    }

    @Override
    public void renderPot(double initialPot, String potFile) {
        myPotView = new PotView(myLanguageBundle, initialPot, potFile);
        myBorderPane.setRight(myPotView.getView());
    }

    @Override
    public void setPot(double newPot) {
        myPotView.setPot(newPot);
    }

    public void classifyHand(String classification, int betID) {
        
    }

    private PlayerView getPlayerView(int playerID) {
        if (myMainPlayer.holdsAPlayer() && myMainPlayer.hasSameID(playerID)) return myMainPlayer.getMainPlayer();
        return myOtherPlayers.getPlayerView(playerID);
    }

    private void updateStyleSheet(String newStylesheet) {
        myVBox.getStylesheets().clear();
        myVBox.getStylesheets().add(newStylesheet);
    }

    private void updateLanguage(String newLanguage) {
        myLanguageBundle.setLanguage(newLanguage);
        myMainPlayer.updateLanguage();
        myOtherPlayers.updateLanguage();
    }

}
