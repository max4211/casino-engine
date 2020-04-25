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

public class WagerSelector {

    private static final String BET_PROMPT_KEY = "BetPrompt";
    private static final CharSequence MIN_STRING = "MIN";
    private static final CharSequence MAX_STRING = "MAX";
    private LanguageBundle myLanguageBundle;

    private static final String WAGER_INPUT_REGEX = "^[0-9]*\\.?[0-9]*$";

    public WagerSelector(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
    }

    public double selectWager(double minBet, double maxBet) throws IncompatibleBetRestrictionException, NoUserInputException, InvalidBetException {
        if (maxBet < minBet) throw new IncompatibleBetRestrictionException();

        Optional<String> mySelection = getWager(minBet, maxBet);
        if (mySelection.isEmpty()) throw new NoUserInputException();
        Double inputtedWager = Double.parseDouble(mySelection.get());
        if (inputtedWager > maxBet || inputtedWager < minBet) throw new InvalidBetException();
        return inputtedWager;
    }


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
