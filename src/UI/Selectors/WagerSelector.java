package UI.Selectors;


import UI.Interfaces.ExceptionCaller;
import UI.LanguageBundle;
import exceptions.IncompatibleBetRestrictionException;
import exceptions.InvalidBetException;
import exceptions.NoUserInputException;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class WagerSelector {

    private static final String BET_PROMPT_KEY = "BetPrompt";
    private static final CharSequence MIN_STRING = "MIN";
    private static final CharSequence MAX_STRING = "MAX";
    private static final double NO_WAGER_INPUT = -1;

    private LanguageBundle myLanguageBundle;

    public WagerSelector(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
    }


    //TODO: just throw the exception? yup. this is stupid
    public double selectWager(double minBet, double maxBet, ExceptionCaller caller) {
        if (maxBet < minBet) {
            caller.sendException(new IncompatibleBetRestrictionException());
        }

        // FIXME: DUPLICATION!
        Optional<String> firstWager = getWager(minBet, maxBet);
        if (firstWager.isPresent()) {
            double suggestedBet = Double.parseDouble(firstWager.get());
            if (suggestedBet >= minBet && suggestedBet <= maxBet) return suggestedBet;
            caller.sendException(new InvalidBetException());
        }

        Optional<String> secondWager = getWager(minBet, maxBet);
        if (secondWager.isPresent()) {
            double suggestedBet = Double.parseDouble(secondWager.get());
            if (suggestedBet >= minBet && suggestedBet <= maxBet) return suggestedBet;
            caller.sendException(new InvalidBetException());
        }

        caller.sendException(new NoUserInputException());
        return NO_WAGER_INPUT;
    }

    private Optional<String> getWager(double minBet, double maxBet) {
        TextInputDialog betAmount = new TextInputDialog(String.valueOf(minBet));
        String actionPrompt = myLanguageBundle.getBundle().getString(BET_PROMPT_KEY);
        actionPrompt = actionPrompt.replace(MIN_STRING, String.valueOf(minBet));
        actionPrompt = actionPrompt.replace(MAX_STRING, String.valueOf(maxBet));
        betAmount.setContentText(actionPrompt);
        return betAmount.showAndWait();
    }
}
