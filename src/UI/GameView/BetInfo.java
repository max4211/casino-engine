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
        hideClassification();
    }

    @Override
    public VBox getView() {
        return myBetInfo;
    }

    public void updateWager(double newWager) {
        myWagerAmount.setText(String.valueOf(newWager));
    }

    public void updateClassification(String newClassification) {
        myClassifierType.setText(newClassification);
    }

    public void hideClassification() {
        myBetInfo.getChildren().remove(myClassificationBox);
    }

    public void showClassification() {
        myBetInfo.getChildren().add(myClassificationBox);
    }

    public void updateLanguage() {
        myWagerLabel.setText(getTranslationFor(WAGER_KEY));
        myClassifierLabel.setText(getTranslationFor(HAND_CLASSIFICATION_KEY));
    }

    public void setWinner() {
        myBetInfo.setId(WINNER_ID);
    }

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

    private void createInfoBox(HBox labelBox, Label descriptionLabel, String descriptiveKey, Label infoLabel) {
        descriptionLabel.setText(myLanguageBundle.getBundle().getString(descriptiveKey));
        labelBox.getChildren().addAll(descriptionLabel, infoLabel);
        labelBox.setAlignment(Pos.CENTER);
        myBetInfo.getChildren().add(labelBox);
    }

    private String getTranslationFor(String key) {
        return myLanguageBundle.getBundle().getString(key);
    }
}

