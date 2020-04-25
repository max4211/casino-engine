package UI.GameView;

import UI.ExceptionDisplay.ExceptionDisplayer;
import UI.Interfaces.GameCaller;
import UI.Interfaces.GameViewInterface;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import UI.Selectors.ActionSelector;
import UI.Selectors.SelectorType;
import UI.Selectors.WagerSelector;
import UI.Settings.SettingsBar;
import UI.Utilities.CardTriplet;
import exceptions.EmptyActionListException;
import exceptions.IncompatibleBetRestrictionException;
import exceptions.InvalidBetException;
import exceptions.NoUserInputException;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.ResourceBundle;

public class GameView implements GameViewInterface, StylizedNode {

    private static final String WEBSITE_URL = "http://casino308.com/";

    private static final String PATH_TO_STYLESHEETS = "styleSheets/game/";
    private static final String PATH_TO_ICON_BUNDLE = "iconBundles/gameBundles/";
    private static final String PATH_TO_ICONS = "iconImages/gameIcons/";

    private static final String NO_ACTION_INPUT = "";
    private static final double NO_WAGER_INPUT = -1;

    private VBox myGameView;
    private BorderPane myBorderPane;
    private MainPlayer myMainPlayer;
    private OtherPlayers myOtherPlayers;
    private HandView myAdversary;
    private HandView myCommons;
    private PotView myPotView;

    private SettingsBar mySettingsBar;
    private LanguageBundle myLanguageBundle;
    private ExceptionDisplayer myExceptionDisplayer;
    private ActionSelector myActionSelector;
    private WagerSelector myWagerSelector;
    private ResourceBundle myIconBundle;

    private static final String ADVERSARY_KEY = "Adversary";
    private static final String COMMON_CARD_KEY = "CommonCard";
    private static final String INFO_KEY = "Info";
    private static final String EXCEPTION_KEY = "Exception";
    private static final String PLAYER_KEY = "Player";
    private static final String POT_KEY = "Pot";
    private static final String HIDDEN_CARD_KEY = "HiddenCard";

    private static final int DEFAULT_LANGUAGE_INDEX = 0;

    public GameView(List<String> styleSheets, List<String> languages, String iconImages, String exceptionCSS, double width, double height) {
        myGameView = new VBox();
        StylizedNode.setStyleID(myGameView, this.getClass());
        Formatter.formatGameView(myGameView, width, height);

        String defaultLanguage = languages.get(DEFAULT_LANGUAGE_INDEX);
        myLanguageBundle = new LanguageBundle(defaultLanguage);

        String iconBundlePath = formatPathToIconBundles(iconImages);
        myIconBundle = ResourceBundle.getBundle(iconBundlePath);

        myBorderPane = new BorderPane();
        myOtherPlayers = new OtherPlayers();
        myBorderPane.setLeft(myOtherPlayers.getView());

        myMainPlayer = new MainPlayer(myLanguageBundle);
        myBorderPane.setBottom(myMainPlayer.getView());

        myExceptionDisplayer = new ExceptionDisplayer(myIconBundle.getString(EXCEPTION_KEY), exceptionCSS, myLanguageBundle);
        myWagerSelector = new WagerSelector(myLanguageBundle);
        myActionSelector = new ActionSelector(myLanguageBundle);
        mySettingsBar = new SettingsBar(e -> updateStyleSheet(e), styleSheets, e -> updateLanguage(e), languages,
                PATH_TO_ICONS.concat(myIconBundle.getString(INFO_KEY)), WEBSITE_URL);

        String pathToInfoIcon = formatPathToIcons(myIconBundle.getString(INFO_KEY));
        mySettingsBar = new SettingsBar(e -> updateStyleSheet(e), styleSheets, e -> updateLanguage(e), languages, pathToInfoIcon, WEBSITE_URL);

        myGameView.getChildren().addAll(mySettingsBar.getView(), myBorderPane);
        myGameView.setVgrow(myBorderPane, Priority.ALWAYS);
    }

    @Override
    public VBox getView() {
        return myGameView;
    }

    @Override
    public void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID) {
        getPlayerView(playerID).addCardIfAbsent(cardInfo, betID, myIconBundle.getString(HIDDEN_CARD_KEY));
    }

    @Override
    public void renderCommonCards(List<CardTriplet> hand) {
        myCommons = new HandView(hand, myIconBundle.getString(HIDDEN_CARD_KEY));
        myBorderPane.setTop(myCommons.getView());
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
    public void addBet(List<CardTriplet> handInfo, double wager, String classification, int playerID, int betID) {
        getPlayerView(playerID).addBet(handInfo, wager, classification, betID, myLanguageBundle, myIconBundle.getString(HIDDEN_CARD_KEY));
    }

    @Override
    public void addBet(double wager, int playerID, int betID) {
        getPlayerView(playerID).addBet(wager, betID, myLanguageBundle);
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
        myAdversary.addCard(cardInfo, myIconBundle.getString(HIDDEN_CARD_KEY));
    }

    @Override
    public void renderAdversary(List<CardTriplet> hand) {
        myAdversary = new HandView(hand, myIconBundle.getString(HIDDEN_CARD_KEY));
        myBorderPane.setTop(myAdversary.getView());
    }

    @Override
    public void addPlayer(String name, int playerId, double bankroll) {
        myOtherPlayers.addPlayer(name, playerId, bankroll, myLanguageBundle);
    }

    @Override
    public void setMainPlayer(int playerID) {
        if (!myOtherPlayers.hasPlayerView(playerID)) return;

        if (myMainPlayer.holdsAPlayer()) {
            myMainPlayer.hideAllClassification();
            myOtherPlayers.addPlayer(myMainPlayer.getMainPlayer());
        }

        myMainPlayer.setMainPlayer(myOtherPlayers.getPlayerView(playerID));
        myMainPlayer.showAllClassification();
        myOtherPlayers.removePlayer(playerID);
    }

    @Override
    public double selectWager(double minBet, double maxBet) {
        myMainPlayer.waitUntilReady(SelectorType.WAGER);
        double chosenWager = NO_WAGER_INPUT;
        try {
            chosenWager = myWagerSelector.selectWager(minBet, maxBet);
        } catch (IncompatibleBetRestrictionException | NoUserInputException | InvalidBetException e) {
            myExceptionDisplayer.displayException(e);
        }
        if (chosenWager != NO_WAGER_INPUT) return chosenWager;
        return selectWager(minBet, maxBet);
    }

    @Override
    public String selectAction(List<String> allActions) {
        myMainPlayer.waitUntilReady(SelectorType.ACTION);
        String actionChosen = NO_ACTION_INPUT;
        try {
            actionChosen = myActionSelector.selectAction(allActions);
        } catch (EmptyActionListException | NoUserInputException e) {
            myExceptionDisplayer.displayException(e);
        }
        if (!actionChosen.equals(NO_ACTION_INPUT)) return actionChosen;
        return selectAction(allActions);
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
    public void displayText(String textToDisplay) {
        Alert display = new Alert(Alert.AlertType.INFORMATION);
        StylizedNode.setStyleID(display.getDialogPane(), display.getClass());
        display.setContentText(textToDisplay);
        display.showAndWait();
    }

    @Override
    public void setLoser(int playerID, int betID) {
        getPlayerView(playerID).setLoser(betID);
    }

    @Override
    public void setWinner(int playerID, int betID) {
        getPlayerView(playerID).setWinner(betID);
    }

    @Override
    public void renderPot(double initialPot) {
        myPotView = new PotView(initialPot, myIconBundle.getString(POT_KEY));
        myBorderPane.setRight(myPotView.getView());
    }

    @Override
    public void setPot(double newPot) {
        myPotView.setPot(newPot);
    }

    @Override
    public void classifyHand(String newClassification, int playerID, int betID) {
        getPlayerView(playerID).updateClassification(betID, newClassification);
    }

    private PlayerView getPlayerView(int playerID) {
        if (myMainPlayer.holdsAPlayer() && myMainPlayer.hasSameID(playerID)) return myMainPlayer.getMainPlayer();
        return myOtherPlayers.getPlayerView(playerID);
    }

    private void updateStyleSheet(String newStylesheet) {
        myGameView.getStylesheets().clear();
        myGameView.getStylesheets().add(formatPathToCSS(newStylesheet));
    }

    private void updateLanguage(String newLanguage) {
        myLanguageBundle.setLanguage(newLanguage);
        myMainPlayer.updateLanguage();
        myOtherPlayers.updateLanguage();
        myExceptionDisplayer.updateLanguage();
    }

    private String formatPathToIconBundles(String iconBundleName) {
        return PATH_TO_ICON_BUNDLE.concat(iconBundleName);
    }

    private String formatPathToIcons(String iconName) {
        return PATH_TO_ICONS.concat(iconName);
    }

    private String formatPathToCSS(String cssFileName) {
        return PATH_TO_STYLESHEETS.concat(cssFileName);
    }
}
