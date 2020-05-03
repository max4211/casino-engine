package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Visual representation of a description of a single bet which is used in the BetView class via composition.
 * Information displayed includes a classification of the hand and the wager attached to it.
 * Implements the StylizedNode interface and returns a VBox containing the information when getView() is called.
 * Implements the LanguageResponder interface and when updateLanguage() is called, the translations from "Wager" and "Classification" are updated.
 * @author Eric Doppelt
 */
public class BetInfo implements StylizedNode, LanguageResponder {

    private VBox myBetInfo;
    private HBox myClassificationBox;
    private Label myWagerLabel;
    private Label myWagerAmount;
    private Label myClassifierLabel;
    private Label myClassifierType;

    private static final String WAGER_KEY = "Wager";
    private static final String HAND_CLASSIFICATION_KEY = "HandClassification";

    private static final String LOSER_ID = "loser-bet-view";
    private static final String WINNER_ID = "winner-bet-view";

    private LanguageBundle myLanguageBundle;

    /**
     * Constructor that initializes the information of the Bet and creates the VBox representing the view.
     * renderInfoBox() is called twice to create HBoxes wrapping information of classification or wager, both of which are contained the VBox.
     * @param wager is a double used to represent the wager of the bet
     * @param classification is the classification of the bet. An emptry string means the bet has not been classified (no cards in it).
     * @param languageBundle is the LanguageBundle that is referenced on language updates, existing as a pointer to a ResourceBundle.
     */
    public BetInfo(double wager, String classification, LanguageBundle languageBundle) {
        initializeBetInfoBox();
        myLanguageBundle = languageBundle;
        initializeInstanceLabels();

        HBox wagerBox = new HBox();
        renderInfoBox(wagerBox, myWagerLabel, WAGER_KEY, myWagerAmount);
        myClassificationBox = new HBox();
        renderInfoBox(myClassificationBox, myClassifierLabel, HAND_CLASSIFICATION_KEY, myClassifierType);

        updateWager(wager);
        updateClassification(classification);
        hideClassification();
    }

    /**
     * Returns a node that represents the BetInfo object which can be changed via update() methods below.
     * @return a VBox containing all the information of wagers and classifications in labels wrapped in HBoxes.
     */
    @Override
    public VBox getView() {
        return myBetInfo;
    }

    /**
     * Alerts the BetInfo that the LanguageBundle it points to may have changed and UI Text should be reflect this.
     * Specifically, this changes the descriptors for Wager and Classification that are shown in the UI.
     */
    @Override
    public void updateLanguage() {
        myWagerLabel.setText(getTranslationFor(WAGER_KEY));
        myClassifierLabel.setText(getTranslationFor(HAND_CLASSIFICATION_KEY));
    }

    /**
     * Sets the wager of the bet displayed to the parameter given. No error checking occurs here, since a negative bet could be created if one loses money on a win.
     * @param newWager is the wager to update the display with.
     */
    public void updateWager(double newWager) {
        myWagerAmount.setText(String.valueOf(newWager));
    }

    /**
     * Sets the classification of the bet to the parameter given. No error checking here since the UI is not aware of any possible classifications for a game.
     * An empty string is used if there is no classification yet.
     * @param newClassification is a String telling the Bet what the classification of its hand is.
     */
    public void updateClassification(String newClassification) {
        myClassifierType.setText(newClassification);
    }

    /**
     * Hides the classification of the hand from being displayed. This is usually called on players in the OtherPlayers display, since their hands should not be displayed.
     * No parameters and no return statement.
     */
    public void hideClassification() {
        myBetInfo.getChildren().remove(myClassificationBox);
    }

    /**
     * Shows the classification of the hand being displayed. This is usually called on players in the MainPlayer display, since they are assumed to be playing the game at that moment.
     */
    public void showClassification() {
        myBetInfo.getChildren().add(myClassificationBox);
    }

    /**
     * Tags the information of the bet as a winner. This is done via means of a CSS ID. Formatting and coloring comes from the stylesheet applied to the GameView object.
     * No parameters and no return. The ID applied is a static final instance variable.
     */
    public void setWinner() {
        myBetInfo.setId(WINNER_ID);
    }

    /**
     * Tags the information of the bet as a loser. This is done via means of a CSS ID. Formatting and coloring comes from the stylesheet applied to the GameView object.
     * No parameters and no return. The ID applied is a static final instance variable.
     */
    public void setLoser() {
        myBetInfo.setId(LOSER_ID);
    }

    private void initializeBetInfoBox() {
        myBetInfo = new VBox();
        Formatter.formatBetInfoBox(myBetInfo);
        StylizedNode.setStyleID(myBetInfo, this.getClass());
    }

    private void initializeInstanceLabels() {
        myWagerLabel = new Label();
        myWagerAmount = new Label();
        myClassifierLabel = new Label();
        myClassifierType = new Label();
    }

    private void renderInfoBox(HBox labelBox, Label descriptionLabel, String descriptiveKey, Label infoLabel) {
        descriptionLabel.setText(myLanguageBundle.getBundle().getString(descriptiveKey));
        labelBox.getChildren().addAll(descriptionLabel, infoLabel);
        labelBox.setAlignment(Pos.CENTER);
        myBetInfo.getChildren().add(labelBox);
    }

    private String getTranslationFor(String key) {
        return myLanguageBundle.getBundle().getString(key);
    }
}

