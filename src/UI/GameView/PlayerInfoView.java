package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerInfoView implements StylizedNode, LanguageResponder {

    private VBox myDetailsBox;
    private Label myNameLabel;
    private Label myBankLabel;
    private Label myBankValue;

    private static final String NAME_KEY = "Name";
    private static final String BANK_KEY = "Bank";

    private static LanguageBundle myLanguageBundle;

    // FOR TESTING
    private static final String NAME_DESCRIPTION_TEST_ID = "#name-label";
    private static final String NAME_VALUE_TEST_ID = "#name-value";
    private static final String BANK_DESCRIPTION_TEST_ID = "#bank-label";
    private static final String BANK_VALUE_TEST_ID = "#bank-value";

    public PlayerInfoView(String name, double bankroll, LanguageBundle languageBundle) {
        myDetailsBox = new VBox();
        StylizedNode.setStyleID(myDetailsBox, this.getClass());
        Formatter.formatPlayerInfoView(myDetailsBox);
        myLanguageBundle = languageBundle;

        // ONLY FOR TESTING (otherwise add a new label to param)
        Label nameValueLabel = new Label();
        nameValueLabel.setId(NAME_VALUE_TEST_ID);
        
        instantiateLabels();
        renderHBox(myNameLabel, NAME_KEY, nameValueLabel, name);
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
        myNameLabel.setId(NAME_DESCRIPTION_TEST_ID);
        myBankLabel = new Label();
        myBankLabel.setId(BANK_DESCRIPTION_TEST_ID);
        myBankValue = new Label();
        myBankLabel.setId(BANK_VALUE_TEST_ID);
    }

    private String getTranslation(String key) {
        return myLanguageBundle.getBundle().getString(key);
    }

    private void renderHBox(Label descriptiveLabel, String bundleKey, Label valueLabel, String userInput) {
        HBox returnedHBox = new HBox();
        descriptiveLabel.setText(getTranslation(bundleKey));
        valueLabel.setText(userInput);
        returnedHBox.getChildren().addAll(descriptiveLabel, valueLabel);
        myDetailsBox.getChildren().add(returnedHBox);
    }

    public void updateBankroll(double amount) {
        myBankLabel.setText(String.valueOf(amount));
    }
}

