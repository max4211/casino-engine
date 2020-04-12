package GameView.Selectors;


import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.ResourceBundle;

public class WagerSelector {

    private static final String BET_PROMPT_KEY = "BetPrompt";
    private static final CharSequence MIN_STRING = "MIN";
    private static final CharSequence MAX_STRING = "MAX";

    private static final String INITIAL_RESOURCE_LANGUAGE = "English";
    private static ResourceBundle myResources = ResourceBundle.getBundle(INITIAL_RESOURCE_LANGUAGE);

    public static double selectWager(double minBet, double maxBet) {
        if (maxBet < minBet) {
            System.out.println("EXECPTION NEEDS TO BE HANDLED");
            return -1;
        }

        TextInputDialog betAmount = new TextInputDialog(String.valueOf(minBet));
        String actionPrompt = myResources.getString(BET_PROMPT_KEY);
        actionPrompt = actionPrompt.replace(MIN_STRING, String.valueOf(minBet));
        actionPrompt = actionPrompt.replace(MAX_STRING, String.valueOf(maxBet));
        betAmount.setContentText(actionPrompt);

        Optional<String> suggestedBetText = betAmount.showAndWait();
        if (suggestedBetText.isPresent()) {
            double suggestedBet = Double.parseDouble(suggestedBetText.get());
            if (suggestedBet >= minBet && suggestedBet <= maxBet) return suggestedBet;
            // TODO: proper convention for one line else statements?
        }
        System.out.println("ENTER A VALID BET");
        return selectWager(minBet, maxBet);
    }
}
