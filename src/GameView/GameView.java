package GameView;

import GameView.Actions.ActionBoxView;
import Utility.CardTriplet;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameView implements GameViewInterface {



    private BorderPane myPane;
    private int DEFAULT_ACTION_INDEX = 0;

    private static final String RESOURCE_LANGUAGE = "English";
    private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LANGUAGE);
    private static final String BET_PROMPT_KEY = "BetPrompt";
    private static final CharSequence MIN_STRING = "MIN";
    private static final CharSequence MAX_STRING = "MAX";
    private static final String ACTION_PROMPT_KEY = "ActionPrompt";

    public GameView() {}

    @Override
    public void renderTable(String file) {

    }

    @Override
    public void addCard(CardTriplet cardInfo, int playerID, int betID) {

    }

    public void renderBet(List<CardTriplet> deckInfo, double wager, int id) {

    }

    @Override
    public void showCard(int betId, int cardId) {

    }

    @Override
    public String getAction(List<String> actions) {
        ChoiceDialog<String> actionOptions = new ChoiceDialog(actions.get(DEFAULT_ACTION_INDEX), actions);
        actionOptions.setContentText(myResources.getString(ACTION_PROMPT_KEY));
        Optional<String> result = actionOptions.showAndWait();
        // TODO: this makes cancelling rerun, find a better way in Sprint 2
        if (result.isEmpty()) return getAction(actions);
        return result.get();
    }

    @Override
    public void renderAdversary(List<CardTriplet> actions) {

    }

    @Override
    public void showAdversaryCard(int cardId) {

    }

    @Override
    public void addBet(List<CardTriplet> deckInfo, double wager, int betID, int playerID) {

    }

    @Override
    public void deleteBet(int playerId, int betId) {

    }

    @Override
    public void addPlayer(int playerId, double bankroll) {

    }

    @Override
    public void removePlayer(int playerId) {

    }

    @Override
    public void updateMainPlayer(int playerId) {

    }


    public double promptPlayerBet(double minBet, double maxBet) {
        TextInputDialog betAmount = new TextInputDialog();
        String actionPrompt = myResources.getString(BET_PROMPT_KEY);
        actionPrompt = actionPrompt.replace(MIN_STRING, String.valueOf(minBet));
        actionPrompt = actionPrompt.replace(MAX_STRING, String.valueOf(maxBet));
        betAmount.setContentText(actionPrompt);

        Optional<String> suggestedBetText = betAmount.showAndWait();
        if (suggestedBetText.isEmpty()) return promptPlayerBet(minBet, maxBet);
        double suggestedBet;
        try {
            suggestedBet = Double.parseDouble(suggestedBetText.get());
        } catch (NumberFormatException ex) {
            // TODO: throw a pop up
            return promptPlayerBet(minBet, maxBet);
        }

        if (suggestedBet >= minBet && suggestedBet <= maxBet) return suggestedBet;
        return promptPlayerBet(minBet, maxBet);
    }
}
