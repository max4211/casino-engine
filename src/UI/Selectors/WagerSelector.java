package UI.Selectors;


import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import exceptions.IncompatibleBetRestrictionException;
import exceptions.InvalidBetException;
import exceptions.NoUserInputException;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.util.converter.DoubleStringConverter;

import java.util.Optional;
import java.util.function.UnaryOperator;

/**
 * Class that encodes functionality in a Dialogue used to select wagers during a round of a card game.
 * Handles languages without the need of the LanguageResponder interface, since Dialogues fetch their text when called, so they are always up-to-date.
 * To use this, simply create a WagerSelector and call the selectWager() method.
 * @author Eric Doppelt
 */
public class WagerSelector {

    private static final String BET_PROMPT_KEY = "BetPrompt";
    private static final CharSequence MIN_STRING = "MIN";
    private static final CharSequence MAX_STRING = "MAX";
    private LanguageBundle myLanguageBundle;

    private static final String WAGER_INPUT_REGEX = "^[0-9]*\\.?[0-9]*$";

    /**
     * Basic constructor that only takes in a LanguageBundle later used to display text in a variety of languages.
     * @param languageBundle is the LanguageBundle that is accessed in subsequent calls of selectWager().
     */
    public WagerSelector(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
    }

    /**
     * Method that encodes the majority of the functionality in this class, prompting the user for input via a Dialogue.
     * A user can only input a wager between the min and max bet values.
     * Text displayed is formatted via a UnaryOperator to only display text in the form of a 0.0 decimal.
     * The only case in which an exception is NOT thrown is when a user inputs a valid wager in the given range.
     * @param minBet is the minimum value a player must enter.
     * @param maxBet is the maximum value a player can enter.
     * @return a double that is the users selection, guaranteed to exist (not be null) if an error is not thrown.
     * @throws IncompatibleBetRestrictionException is thrown if the max bet is less than the min bet (bad input from controller).
     * @throws NoUserInputException is thrown if the user does not enter a wager and instead hits cancel (Bad input from user).
     * @throws InvalidBetException is thrown if the user enters a wager that is not in the specified range.
     */
    public double selectWager(double minBet, double maxBet) throws IncompatibleBetRestrictionException, NoUserInputException, InvalidBetException {
        if (maxBet < minBet) throw new IncompatibleBetRestrictionException();

        Optional<String> mySelection = getWager(minBet, maxBet);
        if (mySelection.isEmpty()) throw new NoUserInputException();
        Double inputtedWager = Double.parseDouble(mySelection.get());
        if (inputtedWager > maxBet || inputtedWager < minBet) throw new InvalidBetException();
        return inputtedWager;
    }

    // Unary Operator Help: https://stackoverflow.com/questions/40472668/numeric-textfield-for-integers-in-javafx-8-with-textformatter-and-or-unaryoperat
    private Optional<String> getWager(double minBet, double maxBet) {
        TextInputDialog betAmount = new TextInputDialog(String.valueOf(minBet));
        StylizedNode.setStyleID(betAmount.getDialogPane(), this.getClass());
        String actionPrompt = myLanguageBundle.getBundle().getString(BET_PROMPT_KEY);
        actionPrompt = actionPrompt.replace(MIN_STRING, String.valueOf(minBet));
        actionPrompt = actionPrompt.replace(MAX_STRING, String.valueOf(maxBet));
        betAmount.setContentText(actionPrompt);

        UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
            String inputtedText = change.getControlNewText();
            if (inputtedText.matches(WAGER_INPUT_REGEX)) {
                return change;
            }
            return null;
        };

        TextFormatter wagerFormatter = new TextFormatter<>(new DoubleStringConverter(), minBet, doubleFilter);
        betAmount.getEditor().setTextFormatter(wagerFormatter);
        return betAmount.showAndWait();
    }
}
