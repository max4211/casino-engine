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
 * Basic visualization of the information regarding a player which is not connected to their bets.
 * This contains a players name and their bankroll.
 * Implements the StylizedNode interface, returning a VBox with the CSS ID PlayerInfo on a getView() method call.
 * Implements the LanguageResponder interface, updating the displayed text to the new language on a method call.
 * This class is used in PlayerView via composition.
 */
public class PlayerInfo implements StylizedNode, LanguageResponder {

    private VBox myDetailsBox;
    private Label myNameLabel;
    private Label myBankLabel;
    private Label myBankValue;

    private static final String NAME_KEY = "Name";
    private static final String BANK_KEY = "Bank";

    private static LanguageBundle myLanguageBundle;

    /**
     * Basic constructor that takes in a name, bankroll, and LanguageBundle.
     * LanguageBundle is used for future updateLanguage() calls. Displayed text is shown in the language that the bundle is currently in.
     * @param name is the name of the player
     * @param bankroll is the bankroll of the player
     * @param languageBundle is the LanguageBundle that the player should accecss whenever there is an updateLanguage() call.
     */
    public PlayerInfo(String name, double bankroll, LanguageBundle languageBundle) {
        myDetailsBox = new VBox();
        StylizedNode.setStyleID(myDetailsBox, this.getClass());
        Formatter.formatPlayerInfoView(myDetailsBox);
        myLanguageBundle = languageBundle;

        instantiateLabels();
        renderHBox(myNameLabel, NAME_KEY, new Label(), name);
        renderHBox(myBankLabel, BANK_KEY, myBankValue, String.valueOf(bankroll));
    }

    /**
     * Returns a VBox containing the name and bankroll of the player, stored in Labels.
     * @return a VBox which is the myDetailsBox instance variable and contains the player information.
     */
    @Override
    public VBox getView() {
        return myDetailsBox;
    }

    /**
     * Updates the text to the new language, as found in the LanguageBundle passed into the constructor.
     * This specifically changes the text used to describe "Bank: " and "Name: ".
     */
    @Override
    public void updateLanguage() {
        myNameLabel.setText(getTranslation(NAME_KEY));
        myBankLabel.setText(getTranslation(BANK_KEY));
    }

    /**
     * Updates the players bankroll to the new amount.
     * There is no amount checking, since it is assumed a player can go into debt.
     * @param amount
     */
    public void updateBankroll(double amount) {
        myBankValue.setText(String.valueOf(amount));
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
}

