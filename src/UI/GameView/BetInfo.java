package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import Utility.Formatter;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BetInfo implements NodeViewInterface, LanguageResponder {

    private VBox myVBox;
    private HBox myWagerBox;
    private HBox myClassifierBox;

    //TODO: BIND THIS?
    private static final double VIEW_HEIGHT = 40;
    private static final double MIN_VIEW_WIDTH = 106;

    private static final Formatter myFormatter = new Formatter();

    //TODO: make a language option that changes this, keeping it static final to draw attention
    private static final String WAGER_KEY = "Wager";
    private static final String HAND_CLASSIFICATION_KEY = "HandClassification";

    private static final String NEUTRAL_ID = "neutral-bet-view";
    private static final String LOSER_ID = "loser-bet-view";
    private static final String WINNER_ID = "winner-bet-view";

    private Label myWagerLabel;
    private Label myWagerAmount;
    private Label myClassifierLabel;
    private Label myClassifierType;

    private LanguageBundle myLanguageBundle;

    public BetInfo(double wager, String classification, LanguageBundle languageBundle) {
        myVBox = new VBox();
        myVBox.setId(NEUTRAL_ID);
        myFormatter.formatFixedVBox(myVBox, VIEW_HEIGHT, MIN_VIEW_WIDTH);
        myLanguageBundle = languageBundle;

        initializeInstanceHBoxes();
        initializeInstanceLabels();
        createInfoBox(myWagerBox, myWagerLabel, WAGER_KEY, myWagerAmount);
        createInfoBox(myClassifierBox, myClassifierLabel, HAND_CLASSIFICATION_KEY, myClassifierType);

        updateWager(wager);
        updateClassification(classification);
    }

    public void updateWager(double newWager) {
        myWagerAmount.setText(String.valueOf(newWager));
    }

    public void updateClassification(String newClassification) {
        myClassifierType.setText(newClassification);
    }

    public void updateLanguage() {
        myWagerLabel.setText(myLanguageBundle.getBundle().getString(WAGER_KEY));
        myClassifierLabel.setText(myLanguageBundle.getBundle().getString(HAND_CLASSIFICATION_KEY));
    }

    public void setWinner() {
        myVBox.setId(WINNER_ID);
    }

    public void setLoser() {
        myVBox.setId(LOSER_ID);
    }

    public VBox getView() {
        return myVBox;
    }

    private void initializeInstanceHBoxes() {
        myWagerBox = new HBox();
        myClassifierBox = new HBox();
    }
    private void initializeInstanceLabels() {
        myWagerLabel = new Label();
        myWagerAmount = new Label();
        myClassifierLabel = new Label();
        myClassifierType = new Label();
    }

    private void createInfoBox(HBox rawHBox, Label descriptionLabel, String descriptiveKey, Label infoLabel) {
        descriptionLabel.setText(myLanguageBundle.getBundle().getString(descriptiveKey));
        rawHBox.getChildren().addAll(descriptionLabel, infoLabel);
        myVBox.getChildren().add(rawHBox);
    }
}

