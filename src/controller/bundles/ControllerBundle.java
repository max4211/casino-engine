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

    @Override
    public Table getTable() {
        return this.myTable;
    }

    @Override
    public GameView getGameView() {
        return this.myGameView;
    }

    @Override
    public Collection<String> getPlayerActions() {
        return this.myPlayerActions;
    }

    @Override
    public List<StringPair> getDealerAction() {
        return this.myDealerAction;
    }

    @Override
    public HandClassifier getHandClassifier() {
        return this.myHandClassifier;
    }

    @Override
    public BetEvaluator getBetEvaluator() {
        return this.myBetEvaluator;
    }

    @Override
    public String getEntryBet() {
        return this.myEntryBet;
    }

    @Override
    public String getCardShow() {
        return this.myCardshow;
    }

    @Override
    public String getGoal() {
        return this.myGoal;
    }
}
