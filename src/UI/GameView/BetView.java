package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.TaggableNode;
import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import UI.Utilities.CardTriplet;
import UI.Utilities.Formatter;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Visual Representation of an entire bet object via means of a JavaFX VBox.
 * This class employs composition by wrapping together a HandView and BetInfo into the VBox. Together they contain all info pertaining to a singular bet.
 * Implements the StylizedNode interface and returns a VBox showing the Bet with a CSS ID of "BetView".
 * Implements the TaggableNode interface and has a hasSameID() method which returns true if the parameter is equal to the Bet's tracking ID given at construction.
 * Implements the LanguageResponder interface and has an updateLanguage() method which simply calls the BetInfo's method of the same name.
 * Most methods called simply call a method of the same name in either the BetInfo or HandView object.
 * @author Eric Doppelt
 */
public class BetView implements StylizedNode, TaggableNode, LanguageResponder {

    private VBox myBetView;
    private HandView myHandView;
    private BetInfo myBetInfo;

    private int myID;
    private int numberOfCards;

    private static final List<CardTriplet> EMPTY_HAND = new ArrayList<>();
    private static final String NO_CLASSIFICATION = "";
    private static final String NO_CARD_IMAGE = "";

    /**
     * Constructor that initializes the VBox returned on a getView() call and nothing more. This packages together a HandView with a BetInfo object.
     * @param hand is the list of information for all cards given at construction. An empty list implies that there are no cards.
     * @param wager is the amount of the bet placed.
     * @param classification is the classification of the hand at construction. An empty String implies that there are no cards.
     * @param id is the ID of the bet. This utilizes the TaggableNode tracking system, as described in the interface with the name.
     * @param languageBundle is the LanguageBundle that the BetInfo object uses. The LanguageBundle passes through this object to the class held via composition.
     * @param cardImage is the image to be used to display the card. This comes from data and is passed into the HandView. It is not stored as an instance variable.
     */
    public BetView(List<CardTriplet> hand, double wager, String classification, int id, LanguageBundle languageBundle, String cardImage) {
        myBetView = new VBox();
        numberOfCards = hand.size();
        Formatter.formatBetView(myBetView, numberOfCards);
        StylizedNode.setStyleID(myBetView, this.getClass());
        myID = id;

        myHandView = new HandView(hand, cardImage);
        myBetInfo = new BetInfo(wager, classification, languageBundle);

        myBetView.getChildren().addAll(myHandView.getView(), myBetInfo.getView());
    }

    /**
     * Alternate constructor to be called when a bet is constructed with no cards. An empty list and empty string are used as the hand and classification, respectively.
     * @param wager is the amount of the bet placed.
     * @param id is the ID of the bet. This utilizes the TaggableNode tracking system, as described in the interface with the name.
     * @param languageBundle is the LanguageBundle that the BetInfo object uses. The LanguageBundle passes through this object to the class held via composition.
     */
    public BetView(double wager, int id, LanguageBundle languageBundle) {
        this(EMPTY_HAND, wager, NO_CLASSIFICATION, id, languageBundle, NO_CARD_IMAGE);
    }

    /**
     * Returns the Node representation of the entire bet via the form of a JavaFX VBox. This is used in GameView to show each individual bet.
     * @return a VBox representing the entire bet object.
     */
    @Override
    public VBox getView() {
        return myBetView;
    }

    /**
     * Returns true if the ID of the Bet object is the same as the parameter.
     * @param ID is the ID to be compared with.
     * @return a boolean signifying if the ID parameter is equal to the ID of the bet held in the myID instance variable.
     */
    @Override
    public boolean hasSameID(int ID) {
        return myID == ID;
    }

    /**
     * Method that is specified by the LanguageResponder interface which alerts the node to update its language based on the LanguageBundle.
     * This simply calls the method of the same name on the BetInfo object it holds.
     */
    @Override
    public void updateLanguage() {
        myBetInfo.updateLanguage();
    }
    /**
     * Allows the wager of the Bet to be updated. This simply calls the method of the same name in the BetInfo object held as an instance variable.
     * @param newAmount is the new wager placed on the bet, given as a double.
     */
    public void updateWager(double newAmount) {
        myBetInfo.updateWager(newAmount);
    }

    /**
     * Allows the classification of the Bet to be updated. This simply calls the method of the same name in the BetInfo object held as an instance variable.
     * @param newClassification is the new String classification of the Hand.
     */
    public void updateClassification(String newClassification) {
        myBetInfo.updateClassification(newClassification);
    }

    /**
     * Adds a card to the HandView object held as an instance variable, regardless of whether it already exists or not.
     * This card is represented via means of a CardView object.
     * @param newCard is a CardTriplet containing all the card information (suit, value, id).
     * @param cardImage is the name of the image to be used to represent the card in a CardView object.
     */
    public void addCard(CardTriplet newCard, String cardImage) {
        numberOfCards++;
        Formatter.updateBetViewWidth(myBetView, numberOfCards);
        myHandView.addCard(newCard, cardImage);
    }

    /**
     * Adds a card to the HandView object held as an instance variable, only if the Hand does not already hold the card.
     * @param newCard is a CardTriplet containing all the card information (suit, value, id).
     * @param cardImage is the name of the image to be used to represent the card in a CardView object.
     */
    public void addCardIfAbsent(CardTriplet newCard, String cardImage) {
        if (!myHandView.hasCard(newCard.getID())) addCard(newCard, cardImage);
    }

    /**
     * Hides the card with the given ID, if it exists.
     * This calls the method of the same name in HandView.
     * @param cardID is an int representing a single card which, if held, is turned to face down (hidden) in the UI.
     */
    public void hideCard(int cardID) {
        myHandView.hideCard(cardID);
    }

    /**
     * Shows the card with the given ID, if it exists.
     * This calls the method of the same name in HandView.
     * @param cardID is an int representing a single card which, if held, is turned to face up (shown) in the UI.
     */
    public void showCard(int cardID) {
        myHandView.showCard(cardID);
    }

    /**
     * Sets the bet to a losing status. This calls a method of the same name in the BetInfo which updates the CSS tag to be that of a loser.
     * Coloring and formatting of losing bets comes from CSS.
     * There are no parameters or return value.
     */
    public void setLoser() {
        myBetInfo.setLoser();
    }

    /**
     * Sets the bet to a winning status. This calls a method of the same name in the BetInfo which updates the CSS tag to be that of a winner.
     * Coloring and formatting of winning bets comes from CSS.
     * There are no parameters or return value.
     */
    public void setWinner() {
        myBetInfo.setWinner();
    }

    /**
     * Calls a method of the same name in the BetInfo object held via composition. This simply removes the String showing the type of hand held in the Bet's cards.
     */
    public void hideClassification() {myBetInfo.hideClassification();}

    /**
     * Calls a method of the same name in the BetInfo object held via composition. This simply adds the String showing the type of hand held in the Bet's cards.
     */
    public void showClassification() {myBetInfo.showClassification();}
}
