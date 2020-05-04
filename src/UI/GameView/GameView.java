package UI.GameView;

import UI.ExceptionDisplay.ExceptionDisplayer;
import UI.Interfaces.GameLauncher;
import UI.Interfaces.GameViewInterface;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import UI.Selectors.ActionSelector;
import UI.Selectors.SelectorType;
import UI.Selectors.WagerSelector;
import UI.Settings.SettingsBar;
import UI.Utilities.CardTriplet;
import engine.dealer.Card;
import exceptions.EmptyActionListException;
import exceptions.IncompatibleBetRestrictionException;
import exceptions.InvalidBetException;
import exceptions.NoUserInputException;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Visual representation of an entire card game, including Players, Bets, Cards, Adversaries, Common Cards, and a Pot.
 * Entire Game is displayed in a VBox, which has a SettingsBar on top and a BorderPane showing the entire game on the bottom.
 * Class is predicated upon heavy composition including PlayerView, MainPlayerView, OtherPlayersView, BetView, PotView, and HandView objects.
 * Most method calls are simply redirected to their proper object that is held via composition.
 * Contains an ExceptionDisplayer, ActionSelector, and WagerSelector to show exceptions and get input in a user-friendly way via composition.
 * Implements GameViewInterface due to the idea of designing by contract.
 * Implements StylizedNode, where the VBox is returned with a CSS IDD of GameView.
 * @author Eric Doppelt
 */
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

    private static final String INFO_KEY = "Info";
    private static final String EXCEPTION_KEY = "Exception";
    private static final String POT_KEY = "Pot";
    private static final String HIDDEN_CARD_KEY = "HiddenCard";

    private static final int DEFAULT_LANGUAGE_INDEX = 0;

    /**
     * Constructor that creates the GameView object.
     * Information passed in tells of CSS files and languages that can be applied to the game, in addition to the icons to use in the UI.
     * No information passed into the constructor defines any type of game play functionality.
     * Formatter object is called to format the Vbox and set its CSS ID to GameView.
     * @param styleSheets is the list of stylesheets that the user can pick from to apply to this view object.
     * @param languages is the list of languages that the user can from to play in.
     * @param iconImages is the name of the ResourceBundle that contains the names of the icons used for the game.
     * @param exceptionCSS is the name of the CSS file that formats exceptions.
     * @param width is the width of the game to display.
     * @param height is the height of the game to display.
     */
    public GameView(List<String> styleSheets, List<String> languages, String iconImages, String exceptionCSS, double width, double height) {
        myGameView = new VBox();
        StylizedNode.setStyleID(myGameView, this.getClass());
        Formatter.formatGameView(myGameView, width, height);

        String defaultLanguage = languages.get(DEFAULT_LANGUAGE_INDEX);
        myLanguageBundle = new LanguageBundle(defaultLanguage);

        String iconBundlePath = formatPathToIconBundles(iconImages);
        myIconBundle = ResourceBundle.getBundle(iconBundlePath);

        myBorderPane = new BorderPane();
        StylizedNode.setStyleID(myBorderPane, myBorderPane.getClass());
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

    /**
     * Method which returns the VBox containing the entire game.
     * @return VBox that holds the entire functionality of the game.
     */
    @Override
    public VBox getView() {
        return myGameView;
    }

    /**
     * Adds a card if it is not already contained by calling the method of the same name on the specified PlayerView object.
     * @param cardInfo is the information of the card to add, if need be, via a CardTriplet.
     * @param playerID is the tracking ID of the player to add the card to.
     * @param betID is the tracking ID of the bet to add the card to.
     */
    @Override
    public void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID) {
        getPlayerView(playerID).addCardIfAbsent(cardInfo, betID, myIconBundle.getString(HIDDEN_CARD_KEY));
    }

    /**
     * Creates a set of common cards displayed in the middle of the BorderPane that are held via a HandView.
     * @param hand is the list of common cards to display in the hand via CardTriplets.
     */
    @Override
    public void renderCommonCards(List<CardTriplet> hand) {
        myCommons = new HandView(hand, myIconBundle.getString(HIDDEN_CARD_KEY));
        myBorderPane.setTop(myCommons.getView());
    }

    /**
     * Shows a common card that is referenced by its tracking ID. It is assumed the card is already held in the Common hand.
     * @param cardID is the card to be shown
     */
    @Override
    public void showCommonCard(int cardID) {
        myCommons.showCard(cardID);
    }

    /**
     * Shows a card held in a player's hand by calling the method of the same name on the appropriate PlayerView object, as specified by the tracking ID.
     * @param playerID is the tracking ID of the player who holds the card
     * @param betID is the tracking ID of the bet which holds the card
     * @param cardID is the tracking ID of the card to be shown
     */
    @Override
    public void showCard(int playerID, int betID, int cardID) {
        getPlayerView(playerID).showCard(betID, cardID);
    }

    /**
     * Hides a card held in a player's hand by calling the method of the same name on the appropriate PlayerView object, as specified by the tracking ID.
     * @param playerID is the tracking ID of the player who holds the card
     * @param betID is the tracking ID of the bet which holds the card
     * @param cardID is the tracking ID of the card to be hidden
     */
    @Override
    public void hideCard(int playerID, int betID, int cardID) {
        getPlayerView(playerID).hideCard(betID, cardID);
    }

    /**
     * Adds a bet with cards to a PlayerView object by calling the method of the same name on the specified PlayerView object.
     * @param handInfo is a list of CardTriplets specifying the cards already in the Bet's hand
     * @param wager is the double amount placed on the bet
     * @param classification is the String classification of the hand of cards
     * @param playerID is the tracking ID for the Player who places the bet
     * @param betID is the tracking ID for the bet placed
     */
    @Override
    public void addBet(List<CardTriplet> handInfo, double wager, String classification, int playerID, int betID) {
        getPlayerView(playerID).addBet(handInfo, wager, classification, betID, myLanguageBundle, myIconBundle.getString(HIDDEN_CARD_KEY));
    }

    /**
     * Adds a bet without cards to a PlayerView object by calling the method of the same name on the specified PlayerView object.
     * @param wager is the double amount placed on the bet
     * @param playerID is the tracking ID for the Player who places the bet
     * @param betID is the tracking ID for the bet placed
     */
    @Override
    public void addBet(double wager, int playerID, int betID) {
        getPlayerView(playerID).addBet(wager, betID, myLanguageBundle);
    }

    /**
     * Removes a bet from a PlayerView object by calling the method of the same name on the specified PlayerView object.
     * @param playerId is the tracking ID of the player from which the bet is removed.
     * @param betId is the tracking ID of the bet to remove.
     */
    @Override
    public void removeBet(int playerId, int betId) {
        getPlayerView(playerId).removeBet(betId);
    }

    /**
     * Clears the bet from every player shown in the UI by calling the method of the same name on the MainPlayer and the OtherPlayers.
     */
    @Override
    public void clearAllBets() {
        if (myMainPlayer.holdsAPlayer()) myMainPlayer.getMainPlayer().clearBets();
        myOtherPlayers.clearBets();
    }

    /**
     * Clears the adversary, if it exists, by calling the method of clearHand() on the HandView object.
     */
    @Override
    public void clearAdversary() {
        if (myAdversary != null) myAdversary.clearHand();
    }

    /**
     * Sets a new wager for a BetView object by calling the updateWager() method on the specified PlayerView object.
     * @param newWager is the new amount to be placed on the specified bet.
     * @param playerID is the tracking ID of the player who holds the bet which needs updating.
     * @param BetID is the tracking ID of the bet which needs updating.
     */
    @Override
    public void setWager(double newWager, int playerID, int BetID) {
        getPlayerView(playerID).updateWager(BetID, newWager);
    }

    /**
     * Sets a new bankroll for a PlayerView object by calling the updateBankRoll() method on the specified PlayerView object.
     * @param newBankroll is the new bankrolll for the specified player.
     * @param playerID is the tracking ID of the player who has a new bankroll.
     */
    @Override
    public void setBankRoll(double newBankroll, int playerID) {
        getPlayerView(playerID).updateBankRoll(newBankroll);
    }

    /**
     * Shows a card held by the adversary by calling the method of the same name on the HandView object.
     * @param cardID is the tracking ID of the card to be shown.
     */
    @Override
    public void showAdversaryCard(int cardID) {
        myAdversary.showCard(cardID);
    }

    /**
     * Adds a card to the adversary by calling the method of the same name on the HandView object.
     * @param cardInfo is a CardTriplet containing the information of the card to be added.
     */
    @Override
    public void addAdversaryCard(CardTriplet cardInfo) {
        myAdversary.addCard(cardInfo, myIconBundle.getString(HIDDEN_CARD_KEY));
    }

    /**
     * Creates the adversary, which is a HandView, and sets it to the top of the BorderPane. This method must be called before cards are added to the adversary.
     * @param hand is the list of information regarding the cards held by the adversary at initialization via CardTriplets
     */
    @Override
    public void renderAdversary(List<CardTriplet> hand) {
        myAdversary = new HandView(hand, myIconBundle.getString(HIDDEN_CARD_KEY));
        myBorderPane.setTop(myAdversary.getView());
    }

    /**
     * Adds a player to the game and places him/her into the OtherPlayers category by calling the method of the same name on the OtherPlayers instance variable.
     * @param name is the name of the new player
     * @param playerId is the tracking ID of the new player
     * @param bankroll is the amount of money held by the new player
     */
    @Override
    public void addPlayer(String name, int playerId, double bankroll) {
        myOtherPlayers.addPlayer(name, playerId, bankroll, myLanguageBundle);
    }

    /**
     * Method which sets the Main Player to the PlayerView specified by the tracking ID given. Change only occurs if OtherPlayers contains the specified PlayerView at first.
     * @param playerID is the tracking ID of the player to place into the MainPlayer position.
     */
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

    /**
     * Prompts the UI to wait for user input via a ReadyButton and then create a dialogue prompting for a bet after doing so.
     * The method does not return until it has a valid bet, but the UI does NOT pause while the Ready Button is waiting.
     * @param minBet is the minimum bet acceptable given via a double
     * @param maxBet is the maximum bet acceptable given via a double
     * @return a double that is the selected bet, assumed to be given by the Main Player.
     */
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

    /**
     * Prompts the UI to wait for user input via a ReadyButton and then create a dialogue prompting for an action from a preset list of choices after doing so.
     * The method does not return until it has a valid action but the UI does NOT pause while the Ready Button is waiting.
     * @param allActions is a list of Strings representing all the actions the User can make at a given time.
     * @return the action selected by the user.
     */
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

    /**
     * Displays an exception by calling the method of the same name on the ExceptionDisplayer instance variable and passing it the exception.
     * @param ex is the exception to be displayed.
     */
    @Override
    public void displayException(Exception ex) {
        myExceptionDisplayer.displayException(ex);
    }

    /**
     * Prompts the user with a button that, when clicked, will launch a new game (as in a new round of the game he/she is playing).
     * @param startNewGame is a GameCaller which is a Functional Interface that defines one method which is assumed to prompt the controller to start a new game.
     */
    @Override
    public void promptNewGame(GameLauncher startNewGame) {
        myMainPlayer.waitUntilReady(SelectorType.NEWGAME);
        startNewGame.startNewGame();
    }

    /**
     * Creates a dialogue to be displayed to the user that simply shows a String of text given by the backend. This is used to show goal updates at the end of rounds.
     * @param textToDisplay is a formatted String to be displayed to the user.
     */
    @Override
    public void displayText(String textToDisplay) {
        Alert display = new Alert(Alert.AlertType.INFORMATION);
        StylizedNode.setStyleID(display.getDialogPane(), display.getClass());
        display.setContentText(textToDisplay);
        display.showAndWait();
    }

    /**
     * Sets the specified bet given to a losing status by calling the method of the same name on the specified PlayerView object and passing it the bet ID.
     * @param playerID is the tracking ID of the player who owns the bet that is to be updated to a loser.
     * @param betID is the tracking ID of the bet that is to be updated to be a loser.
     */
    @Override
    public void setLoser(int playerID, int betID) {
        getPlayerView(playerID).setLoser(betID);
    }

    /**
     * Sets the specified bet given to a winning status by calling the method of the same name on the specified PlayerView object and passing it the bet ID.
     * @param playerID is the tracking ID of the player who owns the bet that is to be updated to a winner.
     * @param betID is the tracking ID of the bet that is to be updated to be a winner.
     */
    @Override
    public void setWinner(int playerID, int betID) {
        getPlayerView(playerID).setWinner(betID);
    }

    /**
     * Creates a pot which appears on the right side of the BorderPane. Does so by simply adding a PotView node.
     * @param initialPot is the amount of money put in the pot at its construction.
     */
    @Override
    public void renderPot(double initialPot) {
        myPotView = new PotView(initialPot, myIconBundle.getString(POT_KEY));
        myBorderPane.setRight(myPotView.getView());
    }

    /**
     * Updates the amount of money in the pot by calling the method of the same name on the PotView object.
     * @param newPot is the new amount of money to be held in the pot.
     */
    @Override
    public void setPot(double newPot) {
        myPotView.setPot(newPot);
    }

    /**
     * Classifies the hand of a specific bet by calling updateClassification() on a specified PlayerView object.
     * @param newClassification is the new classification of the bet, given as a String.
     * @param playerID is the tracking ID of the player who owns the bet to up classified.
     * @param betID is the tracking ID of the bet to classify.
     */
    @Override
    public void classifyHand(String newClassification, int playerID, int betID) {
        getPlayerView(playerID).updateClassification(betID, newClassification);
    }

    /**
     * DEPRECTATED: unsupported method which was supposed to initialize the game, but is now outdated. Use the general constructor and getView() instead.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated @Override
    public void renderGame(File image) throws UnsupportedOperationException { throw new UnsupportedOperationException(); }

    /**
     * DEPRECTATED: unsupported method which was meant to deal a card, but is now outdated. Use addCard() instead.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated @Override
    public void giveCard(BetView b, Card c) throws UnsupportedOperationException {throw new UnsupportedOperationException();}

    /**
     * DEPRECTATED: unsupported method which was supposed to show a card, but is now outdated. Use showCard() using tracking IDs instead.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated @Override
    public void showCard(BetView b, Card c) throws UnsupportedOperationException {throw new UnsupportedOperationException();}

    /**
     * DEPRECTATED: unsupported method which was supposed to show all cards in a bet, but is now outdated. Use showCard() using tracking IDs instead on every card.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated @Override
    public void showCards(BetView b) throws UnsupportedOperationException {throw new UnsupportedOperationException();}

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
