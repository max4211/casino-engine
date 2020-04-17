package UI.GameView;

import UI.Interfaces.NodeViewInterface;
import UI.LanguageBundle;
import Utility.Formatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerInfoView implements NodeViewInterface {

    private VBox myDetailsBox;
    private HBox myNameBox;
    private HBox myBankBox;

    private Formatter myFormatter;

    private static final String NAME_KEY = "Name";
    private static final String BANK_KEY = "Bank";

    // TODO: MAKE A UTILITY CLASS WITH THESE VALUES
    private static final double CARD_HEIGHT = 100;
    private static final double WAGER_HEIGHT = 20;
    private static final double VIEW_WIDTH = 100;

    private static final int RESOURCE_TEXT_INDEX = 0;
    private static final int BANK_NUMBER_INDEX = 1;

    private static LanguageBundle myLanguageBundle;

    private static final String PLAYER_INFO_CSS_ID = "player-info";

    // TODO: noticing some similarities with WagerView, overlap
    public PlayerInfoView(String name, double bankroll, LanguageBundle languageBundle) {
        myDetailsBox = new VBox();
        myDetailsBox.setId(PLAYER_INFO_CSS_ID);
        myFormatter = new Formatter();
        myFormatter.formatFixedVBox(myDetailsBox, CARD_HEIGHT + WAGER_HEIGHT, VIEW_WIDTH);
        myLanguageBundle = languageBundle;

        myNameBox = createHBox(NAME_KEY, name);
        myBankBox = createHBox(BANK_KEY, String.valueOf(bankroll));
    }

    private HBox createHBox(String bundleKey, String userInput) {
        HBox returnedHBox = new HBox();
        returnedHBox.getChildren().add(new Label(myLanguageBundle.getBundle().getString(bundleKey)));
        Label nameInputLabel = new Label(userInput);
        returnedHBox.getChildren().add(nameInputLabel);
        returnedHBox.setAlignment(Pos.CENTER);
        myDetailsBox.getChildren().add(returnedHBox);
        return returnedHBox;
    }

    public VBox getView() {
        return myDetailsBox;
    }

    public void updateLanguage() {
        myNameBox.getChildren().remove(RESOURCE_TEXT_INDEX);
        myNameBox.getChildren().add(RESOURCE_TEXT_INDEX, new Label(myLanguageBundle.getBundle().getString(NAME_KEY)));
        myBankBox.getChildren().remove(RESOURCE_TEXT_INDEX);
        myBankBox.getChildren().add(RESOURCE_TEXT_INDEX, new Label(myLanguageBundle.getBundle().getString(BANK_KEY)));
    }

    public void updateBankroll(double amount) {
        if (myBankBox.getChildren().size() == 2) myBankBox.getChildren().remove(BANK_NUMBER_INDEX);
        myBankBox.getChildren().add(BANK_NUMBER_INDEX, new Label(String.valueOf(amount)));
    }
}

