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
    private static final String MIN_STRING = "MIN";
    private static final String MAX_STRING = "MAX";
    private static final String ACTION_PROMPT_KEY = "ActionPrompt";

    public GameView() {}

    @Override
    public void renderTable(String file) {

    }

    @Override
    public void renderBet(List<CardTriplet> deckInfo, double wager, int id) {

    }

    @Override
    public void addCard(CardTriplet cardInfo) {

    }

    @Override
    public void showCard(int betId, int cardId) {

    }

    @Override
    public String getAction(List<String> actions) {
        ChoiceDialog<String> actionOptions = new ChoiceDialog(actions.get(0), actions);
        actionOptions.setContentText(myResources.getString(ACTION_PROMPT_KEY));
        Optional<String> result = actionOptions.showAndWait();
        if (result.isPresent()) return result.get();
        // TODO: handle not picking an action
        return getAction(actions);
    }

    @Override
    public void renderAdversary(List<CardTriplet> actions) {

    }

    @Override
    public void showAdversaryCard(int cardId) {

    }

    @Override
    public void deleteBet(int betId) {

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

    @Override
    public double promptPlayerBet(int minBet, int maxBet) {
        return 0.0;
    public double promptPlayerBet(double minBet, double maxBet) {
        TextInputDialog betAmount = new TextInputDialog(String.valueOf(minBet));
        String actionPrompt = myResources.getString(BET_PROMPT_KEY);
        actionPrompt.replaceAll(MIN_STRING, String.valueOf(minBet));
        actionPrompt.replaceAll(MAX_STRING, String.valueOf(maxBet));
        betAmount.setContentText(actionPrompt);

        Optional<String> suggestedBetText = betAmount.showAndWait();
        if (suggestedBetText.isPresent()) {
            double suggestedBet = Double.parseDouble(suggestedBetText.get());
            if (suggestedBet >= minBet && suggestedBet <= maxBet) return suggestedBet;
            // TODO: proper convention for one line else statements?
        }
        return promptPlayerBet(minBet, maxBet);
    }
}
