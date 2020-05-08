package controller.bundles;

import UI.GameView.GameView;
import Utility.StringPair;
import controller.enums.Cardshow;
import controller.enums.EntryBet;
import controller.enums.Goal;
import controller.interfaces.ControllerBundleInterface;
import engine.evaluator.bet.BetEvaluator;
import engine.evaluator.handclassifier.HandClassifier;
import engine.table.Table;

import java.util.Collection;
import java.util.List;

/**
 * Bundle all parameters into controller construction to simplify the constructor
 * @author Max Smith
 */
public class ControllerBundle implements ControllerBundleInterface {

    private Table myTable;
    private GameView myGameView;
    private final Collection<String> myPlayerActions;
    private final List<StringPair> myDealerAction;
    private final HandClassifier myHandClassifier;
    private final BetEvaluator myBetEvaluator;

    private final String myEntryBet;
    private String myCardshow;
    private String myGoal;

    /**
     * Constructor with all parameters that make up a controller
     * @param table the table within the game
     * @param gameView the gameview object (UI controller)
     * @param entryBet a string representing the type of entry bet (specific or general)
     * @param playerActions a collection of player actions (alternate with dealer actions)
     * @param dealerAction a collection of dealer actions (quantity, type)
     * @param handClassifier the hand classifier object for the game (classifies hands at every action)
     * @param betEvaluator the bet evaluator object for the game (evaluates bets at the end of the round)
     * @param cardshow the card show policy for the game (e.g. active or other)
     * @param goal the overarching goal for the game (e.g. tournamnet)
     */
    public ControllerBundle(Table table, GameView gameView, String entryBet, Collection<String> playerActions, List<StringPair> dealerAction,
                      HandClassifier handClassifier, BetEvaluator betEvaluator,
                        String cardshow, String goal) {
        this.myTable = table;
        this.myGameView = gameView;
        this.myEntryBet = entryBet;
        this.myPlayerActions = playerActions;
        this.myDealerAction = dealerAction;
        this.myHandClassifier =  handClassifier;
        this.myBetEvaluator = betEvaluator;
        this.myCardshow = cardshow;
        this.myGoal = goal;

    }

    /**
     * @return the table for the game
     */
    @Override
    public Table getTable() {
        return this.myTable;
    }
    /**
     * @return the GameView (UI controller) for the game
     */
    @Override
    public GameView getGameView() {
        return this.myGameView;
    }

    /**
     * @return the collection of player actions (reflection facilitators) for the game
     */
    @Override
    public Collection<String> getPlayerActions() {
        return this.myPlayerActions;
    }

    /**
     * @return the list of dealer actions (type - communal/individual and quantity - any double)
     */
    @Override
    public List<StringPair> getDealerAction() {
        return this.myDealerAction;
    }

    /**
     * @return the hand classfiier module (object) for the game
     */
    @Override
    public HandClassifier getHandClassifier() {
        return this.myHandClassifier;
    }

    /**
     * @return the bet evaluator (used at the end of a round
     */
    @Override
    public BetEvaluator getBetEvaluator() {
        return this.myBetEvaluator;
    }

    /**
     * @return the entry bet for the game (specific or general)
     */
    @Override
    public String getEntryBet() {
        return this.myEntryBet;
    }

    /**
     * @return the card show policy (e.g. all, active or other)
     */
    @Override
    public String getCardShow() {
        return this.myCardshow;
    }

    /**
     * @return the table goal for the game (e.g. tournament or cashgame)
     */
    @Override
    public String getGoal() {
        return this.myGoal;
    }
}
