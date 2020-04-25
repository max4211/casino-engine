package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BetInfo implements StylizedNode, LanguageResponder {

    private VBox myBetInfoBox;
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

    public BetInfo(double wager, String classification, LanguageBundle languageBundle) {
        initializeBetInfoBox();
        myLanguageBundle = languageBundle;
        initializeInstanceLabels();

        HBox wagerBox = new HBox();
        createInfoBox(wagerBox, myWagerLabel, WAGER_KEY, myWagerAmount);
        myClassificationBox = new HBox();
        createInfoBox(myClassificationBox, myClassifierLabel, HAND_CLASSIFICATION_KEY, myClassifierType);

        updateWager(wager);
        updateClassification(classification);
    }

    @Override
    public VBox getView() {
        return myBetInfoBox;
    }

    public void updateWager(double newWager) {
        myWagerAmount.setText(String.valueOf(newWager));
    }

    public void updateClassification(String newClassification) {
        myClassifierType.setText(newClassification);
    }

    public void hideClassification() {
        myBetInfoBox.getChildren().remove(myClassificationBox);
    }

    public void showClassification() {
        myBetInfoBox.getChildren().add(myClassificationBox);
    }

    public void updateLanguage() {
        myWagerLabel.setText(getTranslationFor(WAGER_KEY));
        myClassifierLabel.setText(getTranslationFor(HAND_CLASSIFICATION_KEY));
    }

    public void setWinner() {
        myBetInfoBox.setId(WINNER_ID);
    }

    public void setLoser() {
        myBetInfoBox.setId(LOSER_ID);
    }

    private void initializeBetInfoBox() {
        myBetInfoBox = new VBox();
        Formatter.formatBetInfoBox(myBetInfoBox);
        StylizedNode.setStyleID(myBetInfoBox, this.getClass());
    }

    private void initializeInstanceLabels() {
        myWagerLabel = new Label();
        myWagerAmount = new Label();
        myClassifierLabel = new Label();
        myClassifierType = new Label();
    }

    private void createInfoBox(HBox labelBox, Label descriptionLabel, String descriptiveKey, Label infoLabel) {
        descriptionLabel.setText(myLanguageBundle.getBundle().getString(descriptiveKey));
        labelBox.getChildren().addAll(descriptionLabel, infoLabel);
        labelBox.setAlignment(Pos.CENTER);
        myBetInfoBox.getChildren().add(labelBox);
    }

    private String getTranslationFor(String key) {
        return myLanguageBundle.getBundle().getString(key);
    }
}

