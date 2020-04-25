package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerInfo implements StylizedNode, LanguageResponder {

    private VBox myDetailsBox;
    private Label myNameLabel;
    private Label myBankLabel;
    private Label myBankValue;

    private static final String NAME_KEY = "Name";
    private static final String BANK_KEY = "Bank";

    private static LanguageBundle myLanguageBundle;

    // FOR TESTING


    public PlayerInfo(String name, double bankroll, LanguageBundle languageBundle) {
        myDetailsBox = new VBox();
        StylizedNode.setStyleID(myDetailsBox, this.getClass());
        Formatter.formatPlayerInfoView(myDetailsBox);
        myLanguageBundle = languageBundle;

        // ONLY FOR TESTING (otherwise add a new label to param)

        instantiateLabels();
        renderHBox(myNameLabel, NAME_KEY, new Label(), name);
        renderHBox(myBankLabel, BANK_KEY, myBankValue, String.valueOf(bankroll));
    }

    @Override
    public VBox getView() {
        return myDetailsBox;
    }

    @Override
    public void updateLanguage() {
        myNameLabel.setText(getTranslation(NAME_KEY));
        myBankLabel.setText(getTranslation(BANK_KEY));
    }

    private void instantiateLabels() {
        myNameLabel = new Label();
        myBankLabel = new Label();
        myBankValue = new Label();
    }

    private String getTranslation(String key) {
        return myLanguageBundle.getBundle().getString(key);
    }

    private void renderHBox(Label descriptiveLabel, String bundleKey, Label valueLabel, String userInput) {
        HBox returnedHBox = new HBox();
        returnedHBox.setAlignment(Pos.CENTER);
        descriptiveLabel.setText(getTranslation(bundleKey));
        valueLabel.setText(userInput);
        returnedHBox.getChildren().addAll(descriptiveLabel, valueLabel);
        myDetailsBox.getChildren().add(returnedHBox);
    }

    public void updateBankroll(double amount) {
        myBankValue.setText(String.valueOf(amount));
    }
}

